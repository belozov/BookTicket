package com.example.ticketapp.facade;

import com.example.ticketapp.Decorator.CurrencyDecorator;
import com.example.ticketapp.Decorator.DiscountDecorator;
import com.example.ticketapp.Decorator.KZTPaymentProcessor;
import com.example.ticketapp.Decorator.PaymentProcessor;
import com.example.ticketapp.builder.Ticket;
import com.example.ticketapp.strategy.Seat;
import com.example.ticketapp.factory.TicketFactory;
import com.example.ticketapp.strategy.SeatingStrategy;
import com.example.ticketapp.strategy.TicketReservationSystem;

public class BookingFacade {

    private final TicketReservationSystem reservationSystem;
    private final PaymentProcessor paymentProcessor;
    private final TicketFactory ticketFactory;
    private final SeatingStrategy seatingStrategy;
    private final Seat[] seats;

    public BookingFacade(
            int seatCount,
            SeatingStrategy strategy,
            TicketFactory factory,
            PaymentProcessor payment,
            Seat[] seats
    ) {
        this.seatingStrategy = strategy;
        this.ticketFactory = factory;
        this.paymentProcessor = payment;
        this.seats = seats;

        this.reservationSystem = new TicketReservationSystem(
                seatCount,
                strategy,
                factory,
                seats,
                payment
        );
    }

    public Ticket bookTicket(String userName, String category, String currency) {
        PaymentProcessor configuredPayment = configurePayment(category, currency);

        reservationSystem.setPaymentProcessor(configuredPayment);

        return reservationSystem.reserve(userName);
    }

    private PaymentProcessor configurePayment(String category, String currency) {
        PaymentProcessor processor = new KZTPaymentProcessor();

        if (!category.equalsIgnoreCase("adult")) {
            processor = new DiscountDecorator(processor, category);
        }

        if (currency.equalsIgnoreCase("USD")) {
            processor = new CurrencyDecorator(processor);
        }

        return processor;
    }

    public Seat getLastBookedSeat() {
        return reservationSystem.getLastSeat();
    }

    public int getBookedCount() {
        return reservationSystem.getCount();
    }

    public Seat[] getAllSeats() {
        return seats;
    }

    public void setSeatingStrategy(SeatingStrategy strategy) {
        reservationSystem.setStrategy(strategy);
    }
}