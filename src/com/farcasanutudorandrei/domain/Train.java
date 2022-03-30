package com.farcasanutudorandrei.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Train {
private static int id=1;
private final int trainNumber;
private ArrayList<Employee> trainEmployees;
private Station destinationStation;
private Station initialStation;
private Date departureDate;

    public Train( ArrayList<Employee> trainEmployees, Station destinationStation, Station initialStation, Date departureDate) {
        this.trainNumber = id++;
        this.trainEmployees = trainEmployees;
        this.destinationStation = destinationStation;
        this.initialStation = initialStation;
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber=" + trainNumber +
                ", trainEmployees=" + trainEmployees +
                ", destinationStation=" + destinationStation +
                ", initialStation=" + initialStation +
                ", departureDate=" + departureDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return trainNumber == train.trainNumber && trainEmployees.equals(train.trainEmployees) && destinationStation.equals(train.destinationStation) && initialStation.equals(train.initialStation) && departureDate.equals(train.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, trainEmployees, destinationStation, initialStation, departureDate);
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public ArrayList<Employee> getTrainEmployees() {
        return trainEmployees;
    }

    public void setTrainEmployees(ArrayList<Employee> trainEmployees) {
        this.trainEmployees = trainEmployees;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }

    public Station getInitialStation() {
        return initialStation;
    }

    public void setInitialStation(Station initialStation) {
        this.initialStation = initialStation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
}
