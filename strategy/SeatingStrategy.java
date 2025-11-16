package com.example.ticketapp.strategy;

public interface SeatingStrategy {
    com.example.ticketapp.strategy.Seat chooseSeat(com.example.ticketapp.strategy.Seat[] seats);

    default void printInfo() {
        System.out.println("Using: " + getClass().getSimpleName());
    }
}