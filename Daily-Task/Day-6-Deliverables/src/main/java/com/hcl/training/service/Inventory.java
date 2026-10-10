package com.hcl.training.service;

import com.hcl.training.exception.InsufficientStockException;
import com.hcl.training.exception.InvalidQuantityException;

public final class Inventory {
    private int availableSeats;

    public Inventory(int availableSeats) {
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Available seats cannot be negative");
        }
        this.availableSeats = availableSeats;
    }

    public void reserve(int quantity) throws InsufficientStockException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Booking quantity must be greater than zero");
        }
        if (quantity > availableSeats) {
            throw new InsufficientStockException(
                    "Requested " + quantity + " seats, but only " + availableSeats + " remain");
        }
        availableSeats -= quantity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}
