package com.hcl.training;

import com.hcl.training.entity.*;
import com.hcl.training.payment.*;
import com.hcl.training.strategy.*;

public class Day5Demo {
    public static void main(String[] args) {
        User[] users = { new Admin(1, "Asha"), new Agent(2, "Ravi"), new Traveller(3, "Meera") };
        for (User user : users) System.out.println(user.getRole() + ": " + user.getName());

        Payment[] payments = { new CardPayment(4500), new UpiPayment(3000), new CashPayment(1500) };
        for (Payment payment : payments) System.out.println(payment.pay());

        RefundPolicy policy = new PartialRefundPolicy();
        System.out.println("Refund: ₹" + policy.calculateRefund(4500, 20));
        policy = new FullRefundPolicy();
        System.out.println("Refund: ₹" + policy.calculateRefund(4500, 35));
    }
}
