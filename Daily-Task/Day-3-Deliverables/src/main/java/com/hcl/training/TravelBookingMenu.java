package com.hcl.training;

import java.util.Scanner;

public class TravelBookingMenu {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        String[] features = {
                "Create Packages and Day-wise Itinerary",
                "Schedule Departures with Seat Limits",
                "Search Packages",
                "Book with Co-travellers",
                "Prevent Overbooking",
                "Pay in Instalments",
                "Cancellation and Refund",
                "View Bookings per Departure"
        };

        do {
            System.out.println("\n===== TOUR & TRAVEL PACKAGE BOOKING =====");

            for (int i = 0; i < features.length; i++) {
                System.out.println((i + 1) + ". " + features[i]);
            }

            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("FR1: Package and itinerary management selected.");
                    break;

                case 2:
                    System.out.println("FR2: Departure scheduling selected.");
                    break;

                case 3:
                    System.out.println("FR3: Package search selected.");
                    break;

                case 4:
                    System.out.println("FR4: Co-traveller booking selected.");
                    break;

                case 5:
                    System.out.println("FR5: Overbooking prevention selected.");
                    break;

                case 6:
                    System.out.println("FR6: Instalment payment selected.");
                    break;

                case 7:
                    System.out.println("FR7: Cancellation and refund selected.");
                    break;

                case 8:
                    System.out.println("FR8: Departure booking report selected.");
                    break;

                case 9:
                    running = false;
                    System.out.println("Exiting Tour & Travel Booking System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-9.");
            }

        } while (running);

        scanner.close();
    }
}