package com.hcl.training;

import java.util.ArrayList;
import java.util.List;

public final class MemoryLab {
    private MemoryLab() { }

    public static void main(String[] args) {
        String experiment = args.length == 0 ? "all" : args[0];
        switch (experiment) {
            case "stack" -> stackOverflowExperiment();
            case "oom" -> outOfMemoryExperiment();
            case "strings" -> stringTimingExperiment();
            case "cache" -> integerCacheExperiment();
            case "gc" -> garbageCollectionExperiment();
            case "all" -> {
                stringTimingExperiment();
                integerCacheExperiment();
                garbageCollectionExperiment();
            }
            default -> throw new IllegalArgumentException("Use stack, oom, strings, cache, gc, or all");
        }
    }

    private static void stackOverflowExperiment() {
        try {
            recurse(0);
        } catch (StackOverflowError error) {
            System.out.println("StackOverflowError caught after recursive stack growth");
        }
    }

    private static void recurse(int depth) {
        if (depth % 1000 == 0) System.out.println("stack depth=" + depth);
        recurse(depth + 1);
    }

    private static void outOfMemoryExperiment() {
        List<byte[]> allocations = new ArrayList<>();
        try {
            while (true) allocations.add(new byte[1024 * 1024]);
        } catch (OutOfMemoryError error) {
            System.out.println("OutOfMemoryError caught after allocating about "
                    + allocations.size() + " MiB");
        }
    }

    private static void stringTimingExperiment() {
        int iterations = 25_000;
        long plusStart = System.nanoTime();
        String plus = "";
        for (int i = 0; i < iterations; i++) plus += i;
        long plusNanos = System.nanoTime() - plusStart;

        long builderStart = System.nanoTime();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < iterations; i++) builder.append(i);
        String built = builder.toString();
        long builderNanos = System.nanoTime() - builderStart;

        System.out.println("String += length=" + plus.length() + ", ns=" + plusNanos);
        System.out.println("StringBuilder length=" + built.length() + ", ns=" + builderNanos);
    }

    private static void integerCacheExperiment() {
        Integer cachedA = 127;
        Integer cachedB = 127;
        Integer uncachedA = 1000;
        Integer uncachedB = 1000;
        System.out.println("127 == 127: " + (cachedA == cachedB));
        System.out.println("1000 == 1000: " + (uncachedA == uncachedB));
        System.out.println("1000.equals(1000): " + uncachedA.equals(uncachedB));
    }

    private static void garbageCollectionExperiment() {
        for (int i = 0; i < 40; i++) {
            byte[] shortLived = new byte[256 * 1024];
            if (shortLived.length == 0) System.out.println("unreachable");
        }
        System.gc();
        System.out.println("GC experiment completed; inspect -Xlog:gc output for collector events.");
    }
}
