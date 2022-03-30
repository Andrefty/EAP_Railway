package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Sender extends Person {
    private String phoneNumber;

    public Sender(String name, String firstName, String email, String CNP, String phoneNumber) {
        super(name, firstName, email, CNP);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Sender{" +super.toString()+
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sender)) return false;
        if (!super.equals(o)) return false;
        Sender sender = (Sender) o;
        return phoneNumber.equals(sender.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
