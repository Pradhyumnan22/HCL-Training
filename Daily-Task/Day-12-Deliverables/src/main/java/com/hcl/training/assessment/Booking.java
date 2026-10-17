package com.hcl.training.assessment;

public final class Booking {
    private final long id;
    private final String traveller;
    private final TravelPackage travelPackage;
    private final String departure;
    private final int seats;
    private String status = "CONFIRMED";

    public Booking(long id, String traveller, TravelPackage travelPackage, String departure, int seats) {
        if (seats <= 0) throw new IllegalArgumentException("Seats must be greater than zero");
        this.id = id; this.traveller = traveller; this.travelPackage = travelPackage;
        this.departure = departure; this.seats = seats;
    }
    public long id() { return id; }
    public String traveller() { return traveller; }
    public TravelPackage travelPackage() { return travelPackage; }
    public String departure() { return departure; }
    public int seats() { return seats; }
    public String status() { return status; }
    public void cancel() { status = "CANCELLED"; }
    public double amount() { return seats * travelPackage.price(); }
    @Override public String toString() { return "Booking{id=" + id + ", traveller='" + traveller + "', departure='" + departure + "', seats=" + seats + ", status='" + status + "'}"; }
}
