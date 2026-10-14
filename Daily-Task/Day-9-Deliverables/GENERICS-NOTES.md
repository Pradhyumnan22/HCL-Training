# Day 9 Generics Deliverable

- `Pair<K,V>` demonstrates a generic class with two type parameters.
- `Box<T extends Number>` demonstrates a bounded type.
- `GenericToolkit.max` demonstrates a bounded generic method.
- `copyAll(Collection<? super T>, Collection<? extends T>)` demonstrates PECS wildcards.
- `CrudRepository<T, ID>` and `InMemoryRepository<T, ID>` provide a reusable type-safe repository.
- `CsvSeedLoader` reads `products.csv` with try-with-resources and seeds the repository.
- `AuditResource` demonstrates custom `AutoCloseable` close order.

All collections are parameterized; the project uses no raw types or unchecked conversions.
