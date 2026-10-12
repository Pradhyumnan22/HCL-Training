package com.hcl.training.model;

public record TravelPackage(String code, String destination, int availableSeats) {
    public TravelPackage {
        if (code == null || code.isBlank() || destination == null || destination.isBlank()) {
            throw new IllegalArgumentException("Package code and destination are required");
        }
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Available seats cannot be negative");
        }
    }
}
