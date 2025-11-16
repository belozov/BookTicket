package com.example.ticketapp.strategy;

public class Seat {
    public final int number;
    public final String type;
    public final double priceBonus;
    private boolean booked;

    public Seat(int number, String type, double priceBonus) {
        this.number = number;
        this.type = type;
        this.priceBonus = priceBonus;
        this.booked = false;
    }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }

    @Override
    public String toString() {
        return number + " (" + type + ", +" + priceBonus + "₽)";
    }
}