package com.parkinglot.strategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing cash payment for amount: " + amount);
        return true;
    }
} 