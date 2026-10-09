package com.hcl.training.strategy;

public interface RefundPolicy {
    double calculateRefund(double bookingAmount, int daysBeforeDeparture);
}
