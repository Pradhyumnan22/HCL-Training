package com.hcl.training.strategy;

public final class PartialRefundPolicy implements RefundPolicy {
    @Override public double calculateRefund(double bookingAmount, int daysBeforeDeparture) {
        if (daysBeforeDeparture >= 15) return bookingAmount * 0.75;
        if (daysBeforeDeparture >= 7) return bookingAmount * 0.50;
        return 0;
    }
}
