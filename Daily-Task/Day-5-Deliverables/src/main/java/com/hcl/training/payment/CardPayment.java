package com.hcl.training.payment;

public final class CardPayment extends Payment implements Refundable {
    public CardPayment(double amount) { super(amount); }
    @Override public String pay() { return "Paid ₹" + getAmount() + " by card"; }
    @Override public double refund(double amount) { return Math.min(amount, getAmount()); }
}
