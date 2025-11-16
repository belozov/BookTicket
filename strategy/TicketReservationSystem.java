package com.example.ticketapp.strategy;

import com.example.ticketapp.Decorator.PaymentProcessor;
import com.example.ticketapp.builder.Ticket;
import com.example.ticketapp.factory.TicketFactory;

public class TicketReservationSystem {
    private com.example.ticketapp.strategy.SeatingStrategy strategy;
    private final com.example.ticketapp.strategy.Seat[] seats;
    private int count = 0;
    private TicketFactory factory;
    private com.example.ticketapp.strategy.Seat lastSeat;
    private PaymentProcessor payment;

    public TicketReservationSystem(int seatCount, com.example.ticketapp.strategy.SeatingStrategy strategy, TicketFactory factory, com.example.ticketapp.strategy.Seat[] seats, PaymentProcessor payment) {
        this.strategy = strategy;
        this.factory = factory;
        this.seats = seats;
        this.payment = payment;
    }

    public void setStrategy(com.example.ticketapp.strategy.SeatingStrategy s) {
        this.strategy = s;
    }

    public void setPaymentProcessor(PaymentProcessor payment) {
        this.payment = payment;
    }

    public Ticket reserve(String user) {
        lastSeat = strategy.chooseSeat(seats);
        if (lastSeat == null) return null;

        lastSeat.setBooked(true);
        count++;

        double finalPrice = factory.getBasePrice() + lastSeat.priceBonus;

        boolean paid = payment.pay(finalPrice, "KZT");
        if (!paid) {
            lastSeat.setBooked(false);
            count--;
            return null;
        }

        return factory.create(user, lastSeat.number, finalPrice);
    }

    public com.example.ticketapp.strategy.Seat getLastSeat() { return lastSeat; }
    public int getCount() { return count; }
    public com.example.ticketapp.strategy.Seat[] getSeats() { return seats; }
}