package com.parkinglot.strategy;

public interface PaymentStrategy {
    boolean processPayment(double amount);
} 