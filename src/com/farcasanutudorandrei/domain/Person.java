package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Person {
    protected String name, firstName,email,CNP;

    public Person(String name, String firstName, String email, String CNP) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.CNP = CNP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", CNP='" + CNP + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(firstName, person.firstName) && email.equals(person.email) && Objects.equals(CNP, person.CNP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstName, email, CNP);
    }
}
