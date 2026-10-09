package com.hcl.training.payment;

public final class CashPayment extends Payment {
    public CashPayment(double amount) { super(amount); }
    @Override public String pay() { return "Paid ₹" + getAmount() + " in cash"; }
}
