# Day 12 Assessment 1 — Core Java Checkpoint

This clean console application integrates the travel-booking concepts from Days 1–11.

## Two assessed core flows

1. Package search and booking: searches the Ooty package, books seats, preserves available-seat state, and rejects an overbooking attempt with a checked exception.
2. Cancellation and reporting: cancels a booking using the refund policy, releases seats, calculates the refund, and groups bookings by departure for the admin report.

The project demonstrates plain Java OOP, records, collections, streams, validation, checked exception handling, encapsulation, and defensive list copies.

## Execution

```text
mvn clean package
java -cp target/classes com.hcl.training.assessment.AssessmentApp
```

The repository tag for this assessment is `v1.0-core-java`.
