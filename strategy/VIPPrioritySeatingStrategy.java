package com.example.ticketapp.strategy;

public class VIPPrioritySeatingStrategy implements com.example.ticketapp.strategy.SeatingStrategy {
    @Override
    public com.example.ticketapp.strategy.Seat chooseSeat(com.example.ticketapp.strategy.Seat[] seats) {
        int vipZone = seats.length / 4;
        for (int i = 0; i < vipZone; i++) {
            if (!seats[i].isBooked()) return seats[i];
        }
        for (int i = vipZone; i < seats.length; i++) {
            if (!seats[i].isBooked()) return seats[i];
        }
        return null;
    }
}