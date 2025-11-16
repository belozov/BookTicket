package com.example.ticketapp.Decorator;

public class KZTPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean pay(double amount, String currency) {
        System.out.println("[BasePayment] Paying " + amount + " " + currency);
        return true;
    }
}