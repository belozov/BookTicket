package com.example.ticketapp.Decorator;

public interface PaymentProcessor {
    boolean pay(double amount, String currency);
}
