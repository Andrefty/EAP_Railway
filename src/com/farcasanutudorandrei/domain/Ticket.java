package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Ticket {
private Train train;
private Passenger passenger;
private double price;

    public Ticket(Train train, Passenger passenger, double price) {
        this.train = train;
        this.passenger = passenger;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "train=" + train +
                ", passenger=" + passenger +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0 && train.equals(ticket.train) && passenger.equals(ticket.passenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(train, passenger, price);
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
