package com.hcl.training.assessment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class AssessmentApp {
    private AssessmentApp() { }

    public static void main(String[] args) throws AssessmentException {
        TravelPackage packageOoty = new TravelPackage(1, "Ooty Escape", "Ooty", 4500);
        TravelPackage packageGoa = new TravelPackage(2, "Goa Beach", "Goa", 6000);

        System.out.println("=== FLOW 1: package search and safe booking ===");
        System.out.println("Search destination Ooty: " + List.of(packageOoty));
        Departure ooty = new Departure("DEP-OOTY-01", packageOoty, 5);
        Booking first = ooty.book(101, "Meera", 2);
        System.out.println("Booking confirmed: " + first + ", available=" + ooty.availableSeats());
        try {
            ooty.book(102, "Ravi", 4);
        } catch (AssessmentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== FLOW 2: cancellation refund and departure report ===");
        Departure goa = new Departure("DEP-GOA-01", packageGoa, 10);
        Booking second = goa.book(201, "Asha", 1);
        double refund = cancelWithPolicy(second, goa, 20);
        System.out.println("Cancelled booking=" + second.id() + ", refund=" + refund + ", available=" + goa.availableSeats());
        Map<String, List<Booking>> report = List.of(first, second).stream()
                .collect(Collectors.groupingBy(Booking::departure));
        System.out.println("Admin departure report: " + report);
    }

    private static double cancelWithPolicy(Booking booking, Departure departure, int daysBeforeDeparture) {
        if ("CANCELLED".equals(booking.status())) return 0;
        booking.cancel(); departure.release(booking);
        if (daysBeforeDeparture >= 30) return booking.amount();
        if (daysBeforeDeparture >= 7) return booking.amount() * 0.50;
        return 0;
    }
}
