# Day 15 Optional and Date/Time Deliverable

- `findById` returns `Optional<Subscription>` and never returns `null`.
- `orElseThrow` converts a missing subscription into `SubscriptionNotFoundException`.
- Expiry dates use `ZonedDateTime.plusMonths`.
- Remaining time uses `Duration`.
- Grace periods use `Period`.
- IST/UTC conversion uses `withZoneSameInstant`, preserving the instant.
- `LocalDate`, `LocalDateTime`, `Instant`, and `DateTimeFormatter` are demonstrated.
- No `java.util.Date` is used.
