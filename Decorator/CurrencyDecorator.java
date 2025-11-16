package com.example.ticketapp.Decorator;

public class CurrencyDecorator implements PaymentProcessor {
    private final PaymentProcessor wrappee;
    private static final double USD_TO_KZT = 520.0;

    public CurrencyDecorator(PaymentProcessor wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public boolean pay(double amount, String currency) {
        double amountKZT;
        String logCurrency = currency.toUpperCase();

        if (currency.equalsIgnoreCase("USD")) {
            amountKZT = amount * USD_TO_KZT;
            System.out.println("[Currency] " + amount + " USD → " + amountKZT + " KZT");
        } else {
            amountKZT = amount;
            double usd = amount / USD_TO_KZT;
            System.out.println("[Currency] " + amount + " KZT → " + String.format("%.2f", usd) + " USD");
        }

        return wrappee.pay(amountKZT, "KZT");
    }
}