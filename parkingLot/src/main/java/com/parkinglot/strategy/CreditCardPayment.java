package com.parkinglot.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean processPayment(double amount) {
        // In real implementation, this would integrate with a payment gateway
        System.out.println("Processing credit card payment for amount: " + amount);
        return true;
    }
} 