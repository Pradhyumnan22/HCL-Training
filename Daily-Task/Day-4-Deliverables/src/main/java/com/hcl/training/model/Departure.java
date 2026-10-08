package com.hcl.training.model;

import java.time.LocalDate;
import java.util.Objects;

public class Departure {

    private long id;
    private Package travelPackage;
    private LocalDate departureDate;
    private int totalSeats;
    private int availableSeats;

    public Departure() {
        this(0, null, LocalDate.now(), 1);
    }

    public Departure(Package travelPackage, LocalDate departureDate, int totalSeats) {
        this(0, travelPackage, departureDate, totalSeats);
    }

    public Departure(long id, Package travelPackage, LocalDate departureDate,
                     int totalSeats) {
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Total seats must be greater than zero");
        }

        this.id = id;
        this.travelPackage = travelPackage;
        this.departureDate = departureDate;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Package getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(Package travelPackage) {
        this.travelPackage = travelPackage;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        if (totalSeats <= 0) {
            throw new IllegalArgumentException("Total seats must be greater than zero");
        }

        this.totalSeats = totalSeats;

        if (availableSeats > totalSeats) {
            availableSeats = totalSeats;
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean reserveSeats(int seats) {
        if (seats <= 0) {
            return false;
        }

        if (seats > availableSeats) {
            return false;
        }

        availableSeats -= seats;
        return true;
    }

    public void releaseSeats(int seats) {
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be greater than zero");
        }

        availableSeats = Math.min(availableSeats + seats, totalSeats);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Departure other)) {
            return false;
        }

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Departure{" +
                "id=" + id +
                ", travelPackage=" + travelPackage +
                ", departureDate=" + departureDate +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }
}