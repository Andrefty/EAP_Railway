package com.farcasanutudorandrei.view;

import com.farcasanutudorandrei.domain.*;
import com.farcasanutudorandrei.service.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private Scanner s = new Scanner(System.in);
    private Service service = new Service();

    private ConnectionManager conMan= ConnectionManager.getInstance();

    public static void main(String args[]) {
        Main app = new Main();
        app.conMan.setConnection("jdbc:mysql://192.168.43.215:3306/proiect","ideadata","password");
// TODO: Replace with database
//        app.loadJobs();
//        app.loadPassengers();
//        app.loadSender();
//        app.loadStations();
        while (true) {
            app.showMenu();
            int option = app.readOption();
            app.execute(option);
        }
    }

    private void showMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. add department");
        System.out.println("2. list departments");
        System.out.println("3. add employee");
        System.out.println("4. list employees");
        System.out.println("5. add job");
        System.out.println("6. list jobs");
        System.out.println("7. add parcel");
        System.out.println("8. list parcels");
        System.out.println("9. add passenger");
        System.out.println("10. list passengers");
        System.out.println("11. add sender");
        System.out.println("12. list senders");
        System.out.println("13. add station");
        System.out.println("14. list stations");
        System.out.println("15. add ticket");
        System.out.println("16. list tickets");
        System.out.println("17. add train");
        System.out.println("18. list trains");
        System.out.println("19. show passengers that travel on a given train");
        System.out.println("99. exit");
    }

    private int readOption() {
        try {
            int option = readInt();
            if (option >= 1 /*&& option <= 3*/) {
                return option;
            }
        } catch (RuntimeException invalid) {
            // nothing to do, as it's handled below
        }
        System.out.println("Invalid option. Try again");
        return readOption();
    }

    private void execute(int option) {
        switch (option) {
            case 1:
                // add entity
                addDepartment();
                break;
            case 2:
                // list entities
                service.listDepartments();
                break;
            case 3:
                // add entity
                addEmployee();
                break;
            case 4:
                // list entities
                service.listEmployees();
                break;
            case 5:
                // add entity
                addJob();
                break;
            case 6:
                // list entities
                service.listJobs();
                break;
            case 7:
                // add entity
                addParcel();
                break;
            case 8:
                // list entities
                service.listParcels();
                break;
            case 9:
                // add entity
                addPassenger();
                break;
            case 10:
                // list entities
                service.listPassengers();
                break;
            case 11:
                // add entity
                addSender();
                break;
            case 12:
                // list entities
                service.listSenders();
                break;
            case 13:
                // add entity
                addStation();
                break;
            case 14:
                // list entities
                service.listStations();
                break;
            case 15:
                // add entity
                addTicket();
                break;
            case 16:
                // list entities
                service.listTickets();
                break;
            case 17:
                // add entity
                addTrain();
                break;
            case 18:
                // list entities
                service.listTrains();
                break;
            case 19:
                // show passengers that travel on a given train
                showPassengersOnTrain();
                break;
            case 99:
                System.exit(0);
        }
    }

    private void showPassengersOnTrain() {
        System.out.println("Enter trainNumber:");
        service.listTrains();
        int trainNumber = readInt();
        for (int i = 0; i < service.getTrainSize(); i++) {
            if (service.getTrain(i).getTrainNumber() == trainNumber) {
                for (int j = 0; j < service.getTicketSize(); j++) {
                    if (service.getTicket(j).getTrain().equals(service.getTrain(i))) {
                        System.out.println(service.getTicket(j).getPassenger());
                    }
                }
            }
        }
    }

    private int addTrain() {
        int id = -1;
        System.out.print("departureDate(yyyy-MM-dd HH:mm)=");
        String stringdepartureDate = s.nextLine();
        Date departureDate;
        Station initialStation;
        Station destinationStation;
        ArrayList<Employee> trainEmployees = new ArrayList<Employee>();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            departureDate = df.parse(stringdepartureDate);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
            e.printStackTrace();
            return id;
        }
        System.out.println("Pick initialStation:");
        if (service.getStationSize() == 0) {
            System.out.println("No station available. Add a station? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    initialStation = service.getStation(addStation());
                } catch (RuntimeException e) {
                    System.out.println("Invalid station. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listStations();
            System.out.print("initialStationId=");
            int initialStationId = readInt();
            initialStation = service.getStation(initialStationId);
        }
        System.out.println("Pick destinationStation:");
        if (service.getStationSize() == 0) {
            System.out.println("No station available. Add a station? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    destinationStation = service.getStation(addStation());
                } catch (RuntimeException e) {
                    System.out.println("Invalid station. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listStations();
            System.out.print("destinationStationId=");
            int destinationStationId = readInt();
            destinationStation = service.getStation(destinationStationId);
        }
        System.out.print("Do you want to assign employees? (y/n)");
        String answer = s.nextLine();
        if (answer.equals("y")) {
            if (service.getEmployeeSize() == 0) {
                System.out.println("No employees available. Add a employee? (y/n)");
                String answer1 = s.nextLine();
                if (answer1.equals("y")) {
                    try {
                        trainEmployees.add(service.getEmployee(addEmployee()));
                    } catch (RuntimeException e) {
                        System.out.println("Invalid employee. Try again");
                    }
                } else return id;
            } else {
                System.out.println("How many employees? (Maximum: " + service.getEmployeeSize() + ")");
                int ans = readInt();
                System.out.println("Pick employees:");
                service.listEmployees();
                for (int i = 0; i < ans; i++) {
                    System.out.print("employeeId=");
                    int employeeId = readInt();
                    trainEmployees.add(service.getEmployee(employeeId));
                }

            }
        }
        try {
            id = service.addTrain(new Train(trainEmployees, destinationStation, initialStation, departureDate));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addTicket() {
        int id = -1;
        System.out.print("price=");
        double price = s.nextDouble();
        s.nextLine();
        Train train;
        Passenger passenger;
        System.out.println("Pick a train:");
        if (service.getTrainSize() == 0) {
            System.out.println("No train available. Add a train? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    train = service.getTrain(addTrain());
                } catch (RuntimeException e) {
                    System.out.println("Invalid train. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listTrains();
            System.out.print("trainId=");
            int trainId = readInt();
            train = service.getTrain(trainId);
        }
        System.out.println("Pick a passenger:");
        if (service.getPassengerSize() == 0) {
            System.out.println("No passenger available. Add a passenger? (y/n)");
            String answer1 = s.nextLine();
            if (answer1.equals("y")) {
                try {
                    passenger = service.getPassenger(addPassenger());
                } catch (RuntimeException e) {
                    System.out.println("Invalid passenger. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listPassengers();
            System.out.print("passengerId=");
            int passengerId = readInt();
            passenger = service.getPassenger(passengerId);
        }
        try {
            id = service.addTicket(new Ticket(train, passenger, price));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addPassenger() {
        int id = -1;
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("firstName=");
        String firstName = s.nextLine();
        System.out.print("email=");
        String email = s.nextLine();
        System.out.print("CNP=");
        String cnp = s.nextLine();
        System.out.print("PassengerType=");
        try {
            PassengerType passengerType = PassengerType.valueOf(s.nextLine());
            try {
                id = service.addPassenger(new Passenger(name, firstName, email, cnp, passengerType));
                // TODO: Replace with database
                //  passenger2csv.add("Passenger.csv", new Passenger(name, firstName, email, cnp, passengerType));
            } catch (RuntimeException addError) {
                System.out.println("Add error!");
            }
        } catch (RuntimeException invalidPassengerType) {
            System.out.println("Invalid passengerType!");
        }
        return id;
    }

    private int addParcel() {
        int id = -1;
        System.out.print("weight=");
        double weight = s.nextDouble();
        s.nextLine();
        Sender sender;
        Sender receiver;
        Station departureStation;
        Station destinationStation;
        System.out.println("Pick sender:");
        if (service.getSenderSize() == 0) {
            System.out.println("No sender available. Add a sender? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    sender = service.getSender(addSender());
                } catch (RuntimeException e) {
                    System.out.println("Invalid sender. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listSenders();
            System.out.print("senderId=");
            int senderId = readInt();
            sender = service.getSender(senderId);
        }
        System.out.println("Pick receiver:");
        if (service.getSenderSize() == 0) {
            System.out.println("No receiver available. Add a receiver? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    receiver = service.getSender(addSender());
                } catch (RuntimeException e) {
                    System.out.println("Invalid receiver. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listSenders();
            System.out.print("receiverId=");
            int receiverId = readInt();
            receiver = service.getSender(receiverId);
        }
        System.out.println("Pick departureStation:");
        if (service.getStationSize() == 0) {
            System.out.println("No station available. Add a station? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    departureStation = service.getStation(addStation());
                } catch (RuntimeException e) {
                    System.out.println("Invalid station. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listStations();
            System.out.print("departureStationId=");
            int departureStationId = readInt();
            departureStation = service.getStation(departureStationId);
        }
        System.out.println("Pick destinationStation:");
        if (service.getStationSize() == 0) {
            System.out.println("No station available. Add a station? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    destinationStation = service.getStation(addStation());
                } catch (RuntimeException e) {
                    System.out.println("Invalid station. Try again");
                    return id;
                }
            } else return id;
        } else {
            service.listStations();
            System.out.print("destinationStationId=");
            int destinationStationId = readInt();
            destinationStation = service.getStation(destinationStationId);
        }
        try {
            id = service.addParcel(new Parcel(sender, receiver, departureStation, destinationStation, weight));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addStation() {
        int id = -1;
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("address=");
        String address = s.nextLine();
        try {
            id = service.addStation(new Station(name, address));
            // TODO: Replace with database
            //  station2CSV.add("Station.csv", new Station(name, address));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addSender() {
        int id = -1;
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("firstName=");
        String firstName = s.nextLine();
        System.out.print("email=");
        String email = s.nextLine();
        System.out.print("CNP=");
        String cnp = s.nextLine();
        System.out.println("phoneNumber=");
        String phoneNumber = s.nextLine();
        try {
            id = service.addSender(new Sender(name, firstName, email, cnp, phoneNumber));
            // TODO: Replace with database
            //  sender2CSV.add("Sender.csv", new Sender(name, firstName, email, cnp, phoneNumber));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addEmployee() {
        int id = -1;
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("firstName=");
        String firstName = s.nextLine();
        System.out.print("email=");
        String email = s.nextLine();
        System.out.print("CNP=");
        String cnp = s.nextLine();
        System.out.print("hireDate(yyyy-MM-dd)=");
        String stringhireDate = s.nextLine();
        Date hireDate;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            hireDate = df.parse(stringhireDate);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
            e.printStackTrace();
            return id;
        }
        System.out.print("salary=");
        double salary = s.nextDouble();
        s.nextLine();
        System.out.print("phoneNumber=");
        String phoneNumber = s.nextLine();
        Job job;
        Department department;
        System.out.println("Do you want to assign a job? (y/n)");
        String answer = s.nextLine();
        if (answer.equals("y")) {

            System.out.println("Pick job:");
            if (service.getJobSize() == 0) {
                System.out.println("No job available. Add a job? (y/n)");
                String answer1 = s.nextLine();
                if (answer1.equals("y")) {
                    try {
                        job = service.getJob(addJob());
                    } catch (RuntimeException e) {
                        System.out.println("Invalid job. Try again");
                        return id;
                    }
                } else return id;
            } else {
                service.listJobs();
                System.out.print("jobId=");
                int jobId = readInt();
                job = service.getJob(jobId);
            }
        } else return id;
        System.out.println("Do you want to assign a department to this employee? (y/n)");
        String answer2 = s.nextLine();
        if (answer2.equals("y")) {

            System.out.println("Pick department:");
            if (service.getDepartmentSize() == 0) {
                System.out.println("No department available. Add a department? (y/n)");
                String answer1 = s.nextLine();
                if (answer1.equals("y")) {
                    try {
                        department = service.getDepartment(addDepartment());
                    } catch (RuntimeException e) {
                        System.out.println("Invalid department. Try again");
                        return id;
                    }
                } else return id;
            } else {
                service.listDepartments();
                System.out.print("departmentId=");
                int departmentId = readInt();
                department = service.getDepartment(departmentId);
            }
        } else return id;
        try {
            id = service.addEmployee(new Employee(name, firstName, email, cnp, salary, hireDate, phoneNumber, job, department));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addDepartment() {
        int id = -1;
        System.out.print("name=");
        String name = s.nextLine();
        Employee manager = null;
        System.out.print("Do you want to assign manager? (y/n)");
        String answer = s.nextLine();
        if (answer.equals("y")) {
            System.out.println("Pick manager:");
            if (service.getEmployeeSize() == 0) {
                System.out.println("No employees available. Add a employee? (y/n)");
                String answer1 = s.nextLine();
                if (answer1.equals("y")) {
                    try {
                        manager = service.getEmployee(addEmployee());
                    } catch (RuntimeException e) {
                        System.out.println("Invalid employee. Try again");
                    }
                } else return id;
            } else {
                service.listEmployees();
                System.out.print("employeeId=");
                int employeeId = readInt();
                manager = service.getEmployee(employeeId);
            }
        }
        try {
            id = service.addDepartment(new Department(name, manager));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addJob() {
        int id = -1;
        System.out.print("jobTitle=");
        String jobTitle = s.nextLine();
        System.out.print("jobDescription=");
        String jobDescription = s.nextLine();
        System.out.print("jobLocationType=");
        try {
            JobLocationType jobLocationType = JobLocationType.valueOf(s.nextLine());
            try {
                id = service.addJob(new Job(jobTitle, jobDescription, jobLocationType));
                // TODO: Replace with database
                //  job2csv.add("Job.csv", new Job(jobTitle, jobDescription, jobLocationType));
            } catch (RuntimeException addError) {
                System.out.println("Add error!");
            }
        } catch (RuntimeException invalidJobLocationType) {
            System.out.println("Invalid jobLocationType!");
        }
//Verificare ca functioneaza declarari statice        Job2CSV job2csv=Job2CSV.getInstance();
//        job2csv.testarestatic();
        return id;
    }

    private int readInt() throws RuntimeException {
        String line = s.nextLine();
        if (line.matches("^\\d+$")) {
            return Integer.parseInt(line);
        } else {
            throw new RuntimeException("Invalid number");
        }
    }

}
