package com.hcl.training.strategy;

public final class FullRefundPolicy implements RefundPolicy {
    @Override public double calculateRefund(double bookingAmount, int daysBeforeDeparture) {
        return daysBeforeDeparture >= 30 ? bookingAmount : 0;
    }
}
