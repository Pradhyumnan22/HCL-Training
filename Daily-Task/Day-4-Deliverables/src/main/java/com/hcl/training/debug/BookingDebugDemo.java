package com.hcl.training.debug;

import com.hcl.training.model.Departure;
import com.hcl.training.model.Package;

import java.time.LocalDate;

public class BookingDebugDemo {

    public static void main(String[] args) {

        Package travelPackage = new Package(
                101,
                "Ooty Escape",
                "Ooty",
                4500.0,
                3
        );

        Departure departure = new Departure(
                201,
                travelPackage,
                LocalDate.of(2026, 12, 15),
                20
        );

        System.out.println("Before booking: " + departure.getAvailableSeats());

        int travellers = 25;

        boolean booked = departure.reserveSeats(travellers);

        System.out.println("Booking successful: " + booked);
        System.out.println("After booking: " + departure.getAvailableSeats());

        if (departure.getAvailableSeats() < 0) {
            System.out.println("BUG: Available seats cannot be negative!");
        }
    }
}