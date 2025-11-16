package com.example.ticketapp.builder;

public class Ticket {
    private String type;
    private String userName;
    private int seatNumber;
    private double price;

    public String getType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    private Ticket(Builder b) {
        this.type = b.type;
        this.userName = b.userName;
        this.seatNumber = b.seatNumber;
        this.price = b.price;
    }

    public static class Builder {
        private String type;
        private String userName;
        private int seatNumber;
        private double price;

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setSeatNumber(int seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    @Override
    public String toString() {
        return "Ticket{type='" + type + "', userName='" + userName + "', seatNumber=" + seatNumber + ", price=" + price + '}';
    }
}