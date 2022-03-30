package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Parcel {
    private Sender sender;
    private Sender receiver;
    private Station departureStation;
    private Station destinationStation;
    private double weight;

    public Parcel(Sender sender, Sender receiver, Station departureStation, Station destinationStation, double weight) {
        this.sender = sender;
        this.receiver = receiver;
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", departureStation=" + departureStation +
                ", destinationStation=" + destinationStation +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Double.compare(parcel.weight, weight) == 0 && sender.equals(parcel.sender) && receiver.equals(parcel.receiver) && departureStation.equals(parcel.departureStation) && destinationStation.equals(parcel.destinationStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, departureStation, destinationStation, weight);
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Sender getReceiver() {
        return receiver;
    }

    public void setReceiver(Sender receiver) {
        this.receiver = receiver;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
