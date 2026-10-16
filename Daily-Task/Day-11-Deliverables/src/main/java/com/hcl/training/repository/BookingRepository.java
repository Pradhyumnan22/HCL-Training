package com.hcl.training.repository;

import com.hcl.training.model.Booking;

public final class BookingRepository extends GenericRepository<Booking, Long> {
    public BookingRepository() { super(Booking::getId); }
}
