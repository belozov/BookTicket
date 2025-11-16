package com.example.ticketapp.Decorator;

public class DiscountDecorator implements PaymentProcessor {
    private final PaymentProcessor wrappee;
    private final String category;

    public DiscountDecorator(PaymentProcessor wrappee, String category) {
        this.wrappee = wrappee;
        this.category = category.toLowerCase();
    }

    @Override
    public boolean pay(double amount, String currency) {
        double discountRate = getDiscountRate();
        double discountedAmount = amount * (1 - discountRate);
        System.out.println("[Discount] " + category + " → " + (discountRate * 100) + "% off → " + discountedAmount + " " + currency);
        return wrappee.pay(discountedAmount, currency);
    }

    private double getDiscountRate() {
        switch (category) {
            case "child":
                return 0.6;
            case "student":
            case "pensioner":
                return 0.4;
            default:
                return 0.0;
        }
    }

}