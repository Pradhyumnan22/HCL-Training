package com.hcl.training;

import java.time.ZonedDateTime;

public record Subscription(long id, String traveller, ZonedDateTime startedAt, int durationMonths) {
    public Subscription {
        if (id <= 0 || traveller == null || traveller.isBlank() || startedAt == null || durationMonths <= 0) {
            throw new IllegalArgumentException("Invalid subscription");
        }
    }
}
