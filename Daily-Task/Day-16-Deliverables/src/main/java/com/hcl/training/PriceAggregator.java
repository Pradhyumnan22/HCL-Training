package com.hcl.training;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public final class PriceAggregator {
    private PriceAggregator() { }

    record SupplierResult(String supplier, double price, String error) {
        static SupplierResult success(String supplier, double price) { return new SupplierResult(supplier, price, null); }
        static SupplierResult failure(String supplier, Throwable error) { return new SupplierResult(supplier, 0, error.getMessage()); }
        @Override public String toString() { return error == null ? supplier + "=₹" + price : supplier + "=FAILED(" + error + ")"; }
    }

    public static void main(String[] args) {
        List<Supplier<SupplierResult>> suppliers = List.of(
                () -> slowPrice("Supplier-A", 4200, 1000, false),
                () -> slowPrice("Supplier-B", 3900, 1500, true),
                () -> slowPrice("Supplier-C", 4500, 1200, false));

        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            long sequentialStart = System.nanoTime();
            List<SupplierResult> sequential = suppliers.stream().map(PriceAggregator::runSafely).toList();
            long sequentialMillis = elapsedMillis(sequentialStart);

            long asyncStart = System.nanoTime();
            List<CompletableFuture<SupplierResult>> futures = suppliers.stream()
                    .map(supplier -> CompletableFuture.supplyAsync(supplier, executor)
                            .exceptionally(error -> SupplierResult.failure("unknown", unwrap(error))))
                    .toList();
            List<SupplierResult> async = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                    .thenApply(ignored -> futures.stream().map(CompletableFuture::join).toList()).join();
            long asyncMillis = elapsedMillis(asyncStart);

            System.out.println("Sequential results: " + sequential);
            System.out.println("Async results: " + async);
            System.out.println("Sequential timing: " + sequentialMillis + " ms");
            System.out.println("CompletableFuture timing: " + asyncMillis + " ms");
            System.out.println("Async faster: " + (asyncMillis < sequentialMillis));
            System.out.println("Project availability check: " + parallelAvailabilityCheck(executor));
        } finally {
            executor.shutdown();
        }
    }

    private static SupplierResult runSafely(Supplier<SupplierResult> supplier) {
        try { return supplier.get(); }
        catch (RuntimeException error) { return SupplierResult.failure("sequential-supplier", error); }
    }

    private static SupplierResult slowPrice(String supplier, double price, long delayMillis, boolean fail) {
        try { Thread.sleep(delayMillis); }
        catch (InterruptedException error) { Thread.currentThread().interrupt(); throw new RuntimeException("interrupted", error); }
        if (fail) throw new IllegalStateException("simulated supplier timeout");
        return SupplierResult.success(supplier, price);
    }

    private static List<Boolean> parallelAvailabilityCheck(ExecutorService executor) {
        List<CompletableFuture<Boolean>> checks = List.of(
                CompletableFuture.supplyAsync(() -> true, executor),
                CompletableFuture.supplyAsync(() -> true, executor),
                CompletableFuture.supplyAsync(() -> false, executor));
        return CompletableFuture.allOf(checks.toArray(CompletableFuture[]::new))
                .thenApply(ignored -> checks.stream().map(CompletableFuture::join).toList()).join();
    }

    private static long elapsedMillis(long start) { return (System.nanoTime() - start) / 1_000_000; }
    private static Throwable unwrap(Throwable error) { return error.getCause() == null ? error : error.getCause(); }
}
