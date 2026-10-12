package com.hcl.training.model;

public record WaitlistRequest(String travellerId, String packageCode, int priority) {
    public WaitlistRequest {
        if (travellerId == null || travellerId.isBlank() || packageCode == null || packageCode.isBlank()) {
            throw new IllegalArgumentException("Traveller and package identifiers are required");
        }
    }
}
