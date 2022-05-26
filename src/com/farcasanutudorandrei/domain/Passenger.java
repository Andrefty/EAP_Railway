package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Passenger extends Person {
    private int id_pasager;
    private PassengerType passengerType;

    public Passenger(int id_pasager, String name, String firstName, String email, String CNP, PassengerType passengerType) {
        super(name, firstName, email, CNP);
        this.id_pasager = id_pasager;
        this.passengerType = passengerType;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id_pasager=" + id_pasager+ super.toString() +
                ", passengerType=" + passengerType +
                "} " ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Passenger passenger = (Passenger) o;
        return id_pasager == passenger.id_pasager && passengerType == passenger.passengerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_pasager, passengerType);
    }

    public int getId_pasager() {
        return id_pasager;
    }

    public void setId_pasager(int id_pasager) {
        this.id_pasager = id_pasager;
    }
}
