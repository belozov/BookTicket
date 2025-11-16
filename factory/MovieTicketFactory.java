package com.example.ticketapp.factory;

import com.example.ticketapp.builder.Ticket;

public class MovieTicketFactory implements TicketFactory {
    @Override public Ticket create(String user, int seat, double bonus) {
        return new Ticket.Builder().setType(getEventType()).setUserName(user).setSeatNumber(seat).setPrice(getBasePrice() + bonus).build();
    }
    @Override public double getBasePrice() { return 1800; }
    @Override public String getEventType() { return "Movie"; }
}