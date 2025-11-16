package com.example.ticketapp.factory;

import com.example.ticketapp.builder.Ticket;

public class BusTicketFactory implements TicketFactory {
    @Override
    public Ticket create(String userName, int seatNumber, double bonus) {
        return new Ticket.Builder().setType(getEventType()).setUserName(userName).setSeatNumber(seatNumber).setPrice(getBasePrice() + bonus).build();
    }

    @Override public double getBasePrice() { return 100; }
    @Override public String getEventType() { return "Bus"; }
}