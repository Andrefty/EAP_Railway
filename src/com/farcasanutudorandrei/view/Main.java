package com.farcasanutudorandrei.view;

import com.farcasanutudorandrei.domain.*;
import com.farcasanutudorandrei.service.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private Scanner s = new Scanner(System.in);
    private Service service = new Service();

    public static void main(String args[]) {
        Main app = new Main();
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
        System.out.println("7. exit");
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
            case 99:
                System.exit(0);
        }
    }

    private int addParcel() {
        int id = -1;
        System.out.print("weight=");
        double weight = s.nextDouble();
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
                }
            } else return id;
        }
        service.listSenders();
        System.out.print("senderId=");
        int senderId = s.nextInt();
        sender=service.getSender(senderId);
        System.out.println("Pick receiver:");
        if (service.getSenderSize() == 0) {
            System.out.println("No receiver available. Add a receiver? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    receiver = service.getSender(addSender());
                } catch (RuntimeException e) {
                    System.out.println("Invalid receiver. Try again");
                }
            } else return id;
        }
        service.listSenders();
        System.out.print("receiverId=");
        int receiverId = s.nextInt();
        receiver=service.getSender(receiverId);
        System.out.println("Pick departureStation:");
        if (service.getStationSize() == 0) {
            System.out.println("No station available. Add a station? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    departureStation = service.getStation(addStation());
                } catch (RuntimeException e) {
                    System.out.println("Invalid station. Try again");
                }
            } else return id;
        }
        service.listStations();
        System.out.print("departureStationId=");
        int departureStationId = s.nextInt();
        departureStation=service.getStation(departureStationId);
        System.out.println("Pick destinationStation:");
        if (service.getStationSize() == 0) {
            System.out.println("No station available. Add a station? (y/n)");
            String answer = s.nextLine();
            if (answer.equals("y")) {
                try {
                    destinationStation = service.getStation(addStation());
                } catch (RuntimeException e) {
                    System.out.println("Invalid station. Try again");
                }
            } else return id;
        }
        service.listStations();
        System.out.print("destinationStationId=");
        int destinationStationId = s.nextInt();
        destinationStation=service.getStation(destinationStationId);
        try {
            id = service.addParcel(new Parcel(sender,receiver,departureStation,destinationStation,weight));
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private int addStation() {
        int id = -1;
//        todo
        return id;
    }

    private int addSender() {
        int id = -1;
//        todo
        return id;
    }

    private int addEmployee() {
        int id = -1;
        System.out.print("salary=");
        double salary = s.nextDouble();
        System.out.print("hireDate=");
        String stringhireDate = s.nextLine();
        try {
            Date hireDate = DateFormat.getDateInstance().parse(stringhireDate);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
            e.printStackTrace();
        }
        System.out.print("phoneNumber=");
        String phoneNumber = s.nextLine();
        System.out.println("Do you want to assign a job? (y/n)");
        String answer = s.nextLine();
        if (answer.equals("y")) {
//            todo
        }
        System.out.println("Do you want to assign a department to this employee? (y/n)");
        String answer2 = s.nextLine();
        if (answer2.equals("y")) {
//            todo
        }
        return id;
    }

    private int addDepartment() {
        int id = -1;
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("Do you want to assign manager? (y/n)");
        String answer = s.nextLine();
        if (answer.equals("y")) {
//            todo
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
            } catch (RuntimeException addError) {
                System.out.println("Add error!");
            }
        } catch (RuntimeException invalidJobLocationType) {
            System.out.println("Invalid jobLocationType!");
        }
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
