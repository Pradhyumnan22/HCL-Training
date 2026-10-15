# Day 10 Project Review

## Stack/heap diagram for booking creation

```text
main()
  stack frame
    bookingId, travellerName, bookingAmount
          │ references
          ▼
  new Booking(...) ───────────────► heap object
                                      departure reference ───► Departure heap object
                                      package reference ──────► Package heap object

Local primitive values and object references live in the calling stack frame.
Objects and their fields live on the heap. When a booking is no longer reachable,
it becomes eligible for garbage collection; the stack frame disappears on return.
```

## Project memory-pitfall review

- `Departure` and `Booking` should not retain unnecessary global/static collections.
- Booking cancellation must release references and seats through the domain method rather than duplicating state.
- Repeated string concatenation in reporting should use `StringBuilder`.
- Large result lists should be bounded or streamed so search does not retain every temporary object.
- `Integer` IDs are compared by value with `.equals`, never with `==`.
- `System.gc()` is only a hint; correctness must not depend on it.
