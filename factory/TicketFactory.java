package com.example.ticketapp.factory;

import com.example.ticketapp.builder.Ticket;

public interface TicketFactory {
    Ticket create(String userName, int seatNumber, double bonus);
    double getBasePrice();
    String getEventType();
}
