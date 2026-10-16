package com.hcl.training.service;

import com.hcl.training.model.Booking;
import com.hcl.training.repository.BookingRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BookingService {
    private final BookingRepository repository;
    public BookingService(BookingRepository repository) { this.repository = repository; }
    public void add(Booking booking) { repository.save(booking); }

    // FR7: refund follows the cancellation policy and cancellation is idempotent.
    public double cancel(long bookingId, int daysBeforeDeparture) {
        Booking booking = repository.findById(bookingId).orElseThrow();
        if ("CANCELLED".equals(booking.getStatus())) return 0;
        booking.cancel();
        if (daysBeforeDeparture >= 30) return booking.getAmount();
        if (daysBeforeDeparture >= 7) return booking.getAmount() * 0.50;
        return 0;
    }

    // FR8: admin report groups bookings by departure.
    public Map<String, List<Booking>> bookingsByDeparture() {
        return repository.findAll().stream().collect(Collectors.groupingBy(Booking::getDeparture));
    }
}
