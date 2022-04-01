package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.collections.*;
import com.farcasanutudorandrei.domain.*;

public class Service {
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private JobRepository jobRepository = new JobRepository();
    private ParcelRepository parcelRepository = new ParcelRepository();
    private PassengerRepository passengerRepository = new PassengerRepository();
    private SenderRepository senderRepository = new SenderRepository();
    private StationRepository stationRepository = new StationRepository();
    private TicketRepository ticketRepository = new TicketRepository();
    private TrainRepository trainRepository = new TrainRepository();

    public Passenger getPassenger(int id) {
        return passengerRepository.get(id);
    }

    public int getPassengerSize() {
        return passengerRepository.getSize();
    }

    public Train getTrain(int id) {
        return trainRepository.get(id);
    }

    public int getTrainSize() {
        return trainRepository.getSize();
    }

    public Department getDepartment(int id) {
        return departmentRepository.get(id);
    }

    public int getDepartmentSize(){
        return departmentRepository.getSize();
    }

    public Job getJob(int id) {
        return jobRepository.get(id);
    }

    public int getJobSize() {
        return jobRepository.getSize();
    }

    public Employee getEmployee(int id) {
        return employeeRepository.get(id);
    }

    public int getEmployeeSize() {
        return employeeRepository.getSize();
    }

    public Station getStation(int id) {
        return stationRepository.get(id);
    }

    public int getStationSize() {
        return stationRepository.getSize();
    }

    public Sender getSender(int id) {
        return senderRepository.get(id);
    }

    public int getSenderIndex(Sender name) {
        return senderRepository.getIndex(name);
    }

    public int getSenderSize() {
        return senderRepository.getSize();
    }

    public int addDepartment(Department department) {
        departmentRepository.add(department);
        return departmentRepository.getIndex(department);
    }

    public int addEmployee(Employee employee) {
        employeeRepository.add(employee);
        return employeeRepository.getIndex(employee);
    }

    public int addJob(Job job) {
        jobRepository.add(job);
        return jobRepository.getIndex(job);
    }

    public int addParcel(Parcel parcel) {
        parcelRepository.add(parcel);
        return parcelRepository.getIndex(parcel);
    }

    public int addPassenger(Passenger passenger) {
        passengerRepository.add(passenger);
        return passengerRepository.getIndex(passenger);
    }

    public int addSender(Sender sender) {
        senderRepository.add(sender);
        return senderRepository.getIndex(sender);
    }

    public int addStation(Station station) {
        stationRepository.add(station);
        return stationRepository.getIndex(station);
    }

    public int addTicket(Ticket ticket) {
        ticketRepository.add(ticket);
        return ticketRepository.getIndex(ticket);
    }

    public int addTrain(Train train) {
        trainRepository.add(train);
        return trainRepository.getIndex(train);
    }

    public void listDepartments() {
//        System.out.println(departmentRepository.getAll());
        for (int i = 0; i < departmentRepository.getSize(); i++) {
            System.out.println(i + " " + departmentRepository.get(i));
        }
    }

    public void listEmployees() {
//        System.out.println(employeeRepository.getAll());
        for (int i = 0; i < employeeRepository.getSize(); i++) {
            System.out.println(i + " " + employeeRepository.get(i));
        }
    }

    public void listJobs() {
//        System.out.println(jobRepository.getAll());
        for (int i = 0; i < jobRepository.getSize(); i++) {
            System.out.println(i + " " + jobRepository.get(i));
        }
    }

    public void listParcels() {
//        System.out.println(parcelRepository.getAll());
        for (int i = 0; i < parcelRepository.getSize(); i++) {
            System.out.println(i + " " + parcelRepository.get(i));
        }
    }

    public void listPassengers() {
//        System.out.println(passengerRepository.getAll());
        for (int i = 0; i < passengerRepository.getSize(); i++) {
            System.out.println(i + " " + passengerRepository.get(i));
        }
    }

    public void listSenders() {
//        System.out.println(senderRepository.getAll());
        for (int i = 0; i < senderRepository.getSize(); i++) {
            System.out.println(i + " " + senderRepository.get(i));
        }
    }

    public void listStations() {
//        System.out.println(stationRepository.getAll());
        for (int i = 0; i < stationRepository.getSize(); i++) {
            System.out.println(i + " " + stationRepository.get(i));
        }
    }

    public void listTickets() {
//        System.out.println(ticketRepository.getAll());
        for (int i = 0; i < ticketRepository.getSize(); i++) {
            System.out.println(i + " " + ticketRepository.get(i));
        }
    }

    public void listTrains() {
//        System.out.println(trainRepository.getAll());
        for (int i = 0; i < trainRepository.getSize(); i++) {
            System.out.println(i + " " + trainRepository.get(i));
        }
    }
}
