package com.hcl.training.model;

public final class Booking {
    private final long id;
    private final String traveller;
    private final String departure;
    private final double amount;
    private String status = "CONFIRMED";

    public Booking(long id, String traveller, String departure, double amount) {
        if (id <= 0 || traveller == null || departure == null || amount <= 0) throw new IllegalArgumentException("Invalid booking");
        this.id = id; this.traveller = traveller; this.departure = departure; this.amount = amount;
    }
    public long getId() { return id; }
    public String getTraveller() { return traveller; }
    public String getDeparture() { return departure; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public void cancel() { status = "CANCELLED"; }
    @Override public String toString() { return "Booking{id=" + id + ", traveller='" + traveller + "', departure='" + departure + "', amount=" + amount + ", status='" + status + "'}"; }
}
