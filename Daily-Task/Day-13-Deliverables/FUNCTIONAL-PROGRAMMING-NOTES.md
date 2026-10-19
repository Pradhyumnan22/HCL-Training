# Day 13 Functional Programming Deliverable

`FilterEngine` replaces conditional filtering with composed `Predicate<Product>` values and uses `Function.andThen`/`compose` for transformations. It demonstrates four method-reference forms: static (`FilterEngine::printProduct`), bound instance (`new Printer()::print`), unbound instance (`Product::name`), and constructor (`Printer::new`).

`Consumer` handles output, while `Supplier` provides a lazy default message through `Optional.orElseGet`. The engine contains no anonymous inner classes; each place where a lambda fits uses a lambda or method reference.
