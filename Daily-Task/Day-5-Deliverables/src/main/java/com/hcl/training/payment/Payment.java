package com.hcl.training.payment;

public abstract class Payment {
    private final double amount;

    protected Payment(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        this.amount = amount;
    }

    public double getAmount() { return amount; }
    public abstract String pay();
}
