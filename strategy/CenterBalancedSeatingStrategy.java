package com.example.ticketapp.strategy;

public class CenterBalancedSeatingStrategy implements SeatingStrategy {
    @Override
    public Seat chooseSeat(Seat[] seats) {
        int mid = seats.length / 2;
        for (int offset = 0; offset < seats.length; offset++) {
            int left = mid - offset;
            int right = mid + offset;
            if (left >= 0 && !seats[left].isBooked()) return seats[left];
            if (right < seats.length && !seats[right].isBooked()) return seats[right];
        }
        return null;
    }
}