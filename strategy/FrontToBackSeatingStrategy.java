package com.example.ticketapp.strategy;

public class FrontToBackSeatingStrategy implements SeatingStrategy {
    @Override
    public Seat chooseSeat(Seat[] seats) {
        for (Seat s : seats) {
            if (!s.isBooked()) return s;
        }
        return null;
    }
}