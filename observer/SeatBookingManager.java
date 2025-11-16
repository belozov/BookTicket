package com.example.ticketapp.observer;

import android.util.Log;

import com.example.ticketapp.strategy.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatBookingManager {
    private final List<SeatObserver> observers = new ArrayList<>();

    public void attach(SeatObserver observer) { observers.add(observer); }
    public void detach(SeatObserver observer) { observers.remove(observer); }

    public void bookSeat(Seat seat) {
        if (!seat.isBooked()) {
            seat.setBooked(true);
            Log.d("Booking", "Booked: " + seat);
            notifySeatBooked(seat);
        } else {
            Log.d("Booking", "Already booked: " + seat);
        }
    }

    public void releaseSeat(Seat seat) {
        if (seat.isBooked()) {
            seat.setBooked(false);
            Log.d("Booking", "Released: " + seat);
            notifySeatReleased(seat);
        } else {
            Log.d("Booking", "Already free: " + seat);
        }
    }

    private void notifySeatBooked(Seat seat) {
        for (SeatObserver obs : observers) obs.onSeatBooked(seat);
    }

    private void notifySeatReleased(Seat seat) {
        for (SeatObserver obs : observers) obs.onSeatReleased(seat);
    }
}