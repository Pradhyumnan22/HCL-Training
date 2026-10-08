package com.hcl.training.model;

import java.util.Objects;

public class Booking {

    private long id;
    private Departure departure;
    private String travellerName;
    private int numberOfTravellers;
    private double totalAmount;
    private String status;

    public Booking() {
        this(0, null, "Unknown Traveller", 1, 0.0, "PENDING");
    }

    public Booking(Departure departure, String travellerName, int numberOfTravellers) {
        this(0, departure, travellerName, numberOfTravellers, 0.0, "PENDING");
    }

    public Booking(long id, Departure departure, String travellerName,
                   int numberOfTravellers, double totalAmount, String status) {

        if (numberOfTravellers <= 0) {
            throw new IllegalArgumentException(
                    "Number of travellers must be greater than zero");
        }

        if (totalAmount < 0) {
            throw new IllegalArgumentException(
                    "Total amount cannot be negative");
        }

        this.id = id;
        this.departure = departure;
        this.travellerName = travellerName;
        this.numberOfTravellers = numberOfTravellers;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public String getTravellerName() {
        return travellerName;
    }

    public void setTravellerName(String travellerName) {
        this.travellerName = travellerName;
    }

    public int getNumberOfTravellers() {
        return numberOfTravellers;
    }

    public void setNumberOfTravellers(int numberOfTravellers) {
        if (numberOfTravellers <= 0) {
            throw new IllegalArgumentException(
                    "Number of travellers must be greater than zero");
        }

        this.numberOfTravellers = numberOfTravellers;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException(
                    "Total amount cannot be negative");
        }

        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cancel() {
        if ("CANCELLED".equals(status)) {
            return false;
        }

        status = "CANCELLED";
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Booking other)) {
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
        return "Booking{" +
                "id=" + id +
                ", departure=" + departure +
                ", travellerName='" + travellerName + '\'' +
                ", numberOfTravellers=" + numberOfTravellers +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}