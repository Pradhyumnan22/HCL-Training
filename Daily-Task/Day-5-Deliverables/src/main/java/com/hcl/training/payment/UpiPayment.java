package com.hcl.training.payment;

public final class UpiPayment extends Payment implements Refundable {
    public UpiPayment(double amount) { super(amount); }
    @Override public String pay() { return "Paid ₹" + getAmount() + " by UPI"; }
    @Override public double refund(double amount) { return Math.min(amount, getAmount()); }
}
