package com.hcl.training.service;

import com.hcl.training.exception.InsufficientStockException;
import com.hcl.training.exception.InvalidQuantityException;

import java.util.Scanner;

public final class OrderProcessor {
    private final Inventory inventory;

    public OrderProcessor(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean processOrder(int quantity) {
        try {
            inventory.reserve(quantity);
            System.out.println("Booking confirmed for " + quantity + " seat(s).");
            return true;
        } catch (InsufficientStockException e) {
            System.out.println("Booking rejected: " + e.getMessage());
            return false;
        } catch (InvalidQuantityException e) {
            System.out.println("Invalid booking request: " + e.getMessage());
            return false;
        } finally {
            System.out.println("Audit: available seats = " + inventory.getAvailableSeats());
        }
    }

    public static void main(String[] args) {
        OrderProcessor processor = new OrderProcessor(new Inventory(5));
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Travel Booking Order Processor ===");
            System.out.println("1. Reserve seats");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1" -> {
                        System.out.print("Enter number of seats: ");
                        processor.processOrder(Integer.parseInt(scanner.nextLine()));
                    }
                    case "2" -> running = false;
                    default -> System.out.println("Unknown option. Please choose 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid menu input. Please enter a number.");
            } finally {
                System.out.println("Menu recovered and remains available.");
            }
        }
        scanner.close();
        System.out.println("Order processor closed.");
    }
}
