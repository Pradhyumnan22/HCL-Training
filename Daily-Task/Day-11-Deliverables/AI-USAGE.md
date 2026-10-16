# Day 11 AI Usage and Review Log

AI was used to draft boilerplate and alternative implementations. Every suggestion was compiled, executed, and reviewed against the project requirements.

| # | AI-generated issue found | Review and fix |
|---:|---|---|
| 1 | Initial repository suggestion returned its internal mutable list. | Changed `findAll()` to return `List.copyOf(entities)`. |
| 2 | Initial `Student` draft had no validation for blank names or malformed email. | Added constructor validation for id, name, and email. |
| 3 | Initial entity draft omitted `equals`/`hashCode`, causing duplicate IDs to behave inconsistently in sets. | Implemented both methods using the stable student ID. |
| 4 | Initial cancellation suggestion allowed a booking to be cancelled repeatedly and refunded repeatedly. | Added an idempotency check that returns zero after the first cancellation. |
| 5 | Initial booking report grouped by traveller instead of departure. | Corrected the grouping key to `Booking::getDeparture` and verified FR8 output. |
| 6 | Initial repository sketch used a raw `Repository` type. | Replaced it with `GenericRepository<T, ID>` and typed `StudentRepository`/`BookingRepository`. |

## Review conclusion

The final code has encapsulated fields, immutable student data, defensive repository results, typed repositories, validation, explicit refund behavior, and executable FR7–FR8 demonstrations.
