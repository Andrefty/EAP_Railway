package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Sender extends Person {
    private int id_expeditor;
    private String phoneNumber;

    public Sender(int id_expeditor, String name, String firstName, String email, String CNP, String phoneNumber) {
        super(name, firstName, email, CNP);
        this.id_expeditor = id_expeditor;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id_expeditor=" + id_expeditor + super.toString()+
                ", phoneNumber='" + phoneNumber + '\'' +
                "} " ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sender sender = (Sender) o;
        return id_expeditor == sender.id_expeditor && Objects.equals(phoneNumber, sender.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id_expeditor, phoneNumber);
    }

    public int getId_expeditor() {
        return id_expeditor;
    }

    public void setId_expeditor(int id_expeditor) {
        this.id_expeditor = id_expeditor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
