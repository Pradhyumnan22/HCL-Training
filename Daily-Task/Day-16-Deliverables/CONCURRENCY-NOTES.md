# Day 16 Concurrency Essentials

`PriceAggregator` fetches three simulated supplier prices. The sequential pipeline waits for each supplier, while the asynchronous pipeline uses a fixed executor and `CompletableFuture.supplyAsync` for concurrent I/O-like work. `allOf` waits for every future and `thenApply` collects their completed results.

One supplier intentionally fails. Both sequential and asynchronous execution convert that failure into a `SupplierResult` instead of crashing the application. The project hook uses the same `CompletableFuture.allOf` pattern for independent package-availability checks.

The asynchronous version is expected to take approximately the slowest supplier delay rather than the sum of all delays. Timings are runtime-dependent and are printed by the demo.
