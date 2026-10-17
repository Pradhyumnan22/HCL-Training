package com.hcl.training.assessment;

import java.util.ArrayList;
import java.util.List;

public final class Departure {
    private final String code;
    private final TravelPackage travelPackage;
    private int availableSeats;
    private final List<Booking> bookings = new ArrayList<>();

    public Departure(String code, TravelPackage travelPackage, int seats) {
        this.code = code; this.travelPackage = travelPackage; this.availableSeats = seats;
    }
    public Booking book(long id, String traveller, int seats) throws AssessmentException {
        if (seats > availableSeats) throw new AssessmentException("Overbooking prevented: only " + availableSeats + " seats remain");
        Booking booking = new Booking(id, traveller, travelPackage, code, seats);
        bookings.add(booking); availableSeats -= seats; return booking;
    }
    public void release(Booking booking) { availableSeats += booking.seats(); }
    public String code() { return code; }
    public int availableSeats() { return availableSeats; }
    public List<Booking> bookings() { return List.copyOf(bookings); }
}
