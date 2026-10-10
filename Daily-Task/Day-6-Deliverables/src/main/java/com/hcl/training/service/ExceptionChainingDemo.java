package com.hcl.training.service;

import com.hcl.training.exception.InsufficientStockException;

public final class ExceptionChainingDemo {
    private ExceptionChainingDemo() { }

    public static void main(String[] args) {
        try {
            loadBookingData();
        } catch (InsufficientStockException e) {
            System.out.println("Handled chained exception: " + e.getMessage());
            System.out.println("Root cause: " + e.getCause().getMessage());
        }
    }

    private static void loadBookingData() throws InsufficientStockException {
        try {
            Integer.parseInt("not-a-number");
        } catch (NumberFormatException e) {
            throw new InsufficientStockException("Could not load booking inventory", e);
        }
    }
}
