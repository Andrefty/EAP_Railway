package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Passenger extends Person {

    private PassangerType passengerType;

    public Passenger(String name, String firstName, String email, String CNP, PassangerType passengerType) {
        super(name, firstName, email, CNP);
        this.passengerType = passengerType;
    }

    public PassangerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassangerType passengerType) {
        this.passengerType = passengerType;
    }

    @Override
    public String toString() {
        return "Passenger{" +super.toString()+
                "passengerType=" + passengerType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        if (!super.equals(o)) return false;
        Passenger passenger = (Passenger) o;
        return passengerType == passenger.passengerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerType);
    }
}
