package com.hcl.training.model;

import java.util.Objects;

public class Package {

    private long id;
    private String name;
    private String destination;
    private double price;
    private int durationDays;

    public Package() {
        this(0, "Unknown Package", "Unknown", 0.0, 1);
    }

    public Package(String name, String destination) {
        this(0, name, destination, 0.0, 1);
    }

    public Package(long id, String name, String destination,
                   double price, int durationDays) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.durationDays = durationDays;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        if (durationDays <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }
        this.durationDays = durationDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Package other)) {
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
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", durationDays=" + durationDays +
                '}';
    }
}