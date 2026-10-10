# Day 6 AI-Assisted Stack-Trace Notes

The following two traces were generated from the Day 6 examples and reviewed by tracing from the bottom frame upward. The explanations were checked against the source code and Maven execution output.

## Trace 1 — invalid quantity

```text
com.hcl.training.exception.InvalidQuantityException: Booking quantity must be greater than zero
    at com.hcl.training.service.Inventory.reserve(Inventory.java:19)
    at com.hcl.training.service.OrderProcessor.processOrder(OrderProcessor.java:19)
```

Explanation: `processOrder(0)` calls `Inventory.reserve(0)`. The validation rule rejects zero before stock is changed. The unchecked exception is handled by the multi-catch branch, the `finally` audit runs, and the menu remains usable.

## Trace 2 — chained loading failure

```text
com.hcl.training.exception.InsufficientStockException: Could not load booking inventory
    at com.hcl.training.service.ExceptionChainingDemo.loadBookingData(ExceptionChainingDemo.java:19)
    at com.hcl.training.service.ExceptionChainingDemo.main(ExceptionChainingDemo.java:10)
Caused by: java.lang.NumberFormatException: For input string: "not-a-number"
    at java.base/java.lang.Integer.parseInt(Integer.java:668)
```

Explanation: parsing fails first. `loadBookingData` preserves that root cause using exception chaining while exposing the domain-level checked exception. The caller catches `InsufficientStockException` and can inspect `getCause()`.

## Verification checklist

- Checked `InsufficientStockException` is declared and caught.
- Unchecked `InvalidQuantityException` is handled without an empty catch.
- Multi-catch handles invalid quantity and malformed menu numbers.
- `finally` always prints the audit state.
- The menu loop continues after an error.
