# Day 14 Streams API Deliverable

`SalesAnalytics` creates 1,000 deterministic travel orders and uses streams for every report:

- `groupingBy` + `summingDouble` calculates revenue by category.
- `groupingBy` + `summingDouble` followed by sorting calculates the top three customers.
- `partitioningBy` separates paid and non-paid orders.
- `mapToInt().average()` calculates average basket size.
- `flatMap`, `distinct`, and `map` join order product codes to product names.
- Sequential and parallel streams run the same CPU-heavy calculation for timing comparison.

The stream pipelines do not mutate external collections. Parallel streams are used only for the independent CPU-heavy calculation, where each order is processed without shared mutable state.
