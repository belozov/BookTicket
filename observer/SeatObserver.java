package com.example.ticketapp.observer;

import com.example.ticketapp.strategy.Seat;

public interface SeatObserver {
    void onSeatBooked(Seat seat);
    void onSeatReleased(Seat seat);
}