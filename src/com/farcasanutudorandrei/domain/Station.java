package com.farcasanutudorandrei.domain;

import java.util.Objects;

public class Station {
    private int id_station;
    private String name;
    private String address;

    public Station(int id_station, String name, String address) {
        this.id_station = id_station;
        this.name = name;
        this.address = address;
    }
//    public Station(String name, String address) {
//        this.name = name;
//        this.address = address;
//    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id_station == station.id_station && name.equals(station.name) && address.equals(station.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_station, name, address);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id_station=" + id_station +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
