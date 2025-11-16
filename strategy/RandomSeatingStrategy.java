package com.example.ticketapp.strategy;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSeatingStrategy implements SeatingStrategy {
    private final Random random = new Random();

    @Override
    public Seat chooseSeat(Seat[] seats) {
        List<Seat> free = new ArrayList<>();
        for (Seat s : seats) {
            if (!s.isBooked()) free.add(s);
        }
        if (free.isEmpty()) return null;
        return free.get(random.nextInt(free.size()));
    }
}