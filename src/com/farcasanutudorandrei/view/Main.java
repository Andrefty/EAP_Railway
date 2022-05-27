package com.farcasanutudorandrei.view;

import com.farcasanutudorandrei.domain.*;
import com.farcasanutudorandrei.service.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private Scanner s = new Scanner(System.in);
    private Service service = Service.getInstance();
    private Sender2DB sender2DB = Sender2DB.getInstance();
    private Job2DB job2DB = Job2DB.getInstance();
    private Station2DB station2DB = Station2DB.getInstance();
    private Passenger2DB passenger2DB = Passenger2DB.getInstance();
    private ConnectionManager conMan = ConnectionManager.getInstance();

    public static void main(String args[]) {
        Main app = new Main();
        app.conMan.setConnection("jdbc:mysql://192.168.43.215:3306/proiect", "ideadata", "password");
        app.job2DB.load();
        app.passenger2DB.load();
        app.sender2DB.load();
        app.station2DB.load();
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
        System.out.println("6. edit job");
        System.out.println("7. delete job");
        System.out.println("8. list jobs");
        System.out.println("9. add parcel");
        System.out.println("10. list parcels");
        System.out.println("11. add passenger");
        System.out.println("12. edit passenger");
        System.out.println("13. delete passenger");
        System.out.println("14. list passengers");
        System.out.println("15. add sender");
        System.out.println("16. edit sender");
        System.out.println("17. delete sender");
        System.out.println("18. list senders");
        System.out.println("19. add station");
        System.out.println("20. edit station");
        System.out.println("21. delete station");
        System.out.println("22. list stations");
        System.out.println("23. add ticket");
        System.out.println("24. list tickets");
        System.out.println("25. add train");
        System.out.println("26. list trains");
        System.out.println("27. show passengers that travel on a given train");
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
            case 1 ->
                // add entity
                    addDepartment();
            case 2 ->
                // list entities
                    service.listDepartments();
            case 3 ->
                // add entity
                    addEmployee();
            case 4 ->
                // list entities
                    service.listEmployees();
            case 5 ->
                // add entity
                    addJob();
            case 6 ->
                // edit entity
                    editJob();
            case 7 ->
                // delete entity
                    deleteJob();
            case 8 ->
                // list entities
                    service.listJobs();
            case 9 ->
                // add entity
                    addParcel();
            case 10 ->
                // list entities
                    service.listParcels();
            case 11 ->
                // add entity
                    addPassenger();
            case 12 ->
                // edit entity
                    editPassenger();
            case 13 ->
                // delete entity
                    deletePassenger();
            case 14 ->
                // list entities
                    service.listPassengers();
            case 15 ->
                // add entity
                    addSender();
            case 16 ->
                // add entity
                    editSender();
            case 17 ->
                // add entity
                    deleteSender();
            case 18 ->
                // list entities
                    service.listSenders();
            case 19 ->
                // add entity
                    addStation();
            case 20 ->
                // edit entity
                    editStation();
            case 21 ->
                // delete entity
                    deleteStation();
            case 22 ->
                // list entities
                    service.listStations();
            case 23 ->
                // add entity
                    addTicket();
            case 24 ->
                // list entities
                    service.listTickets();
            case 25 ->
                // add entity
                    addTrain();
            case 26 ->
                // list entities
                    service.listTrains();
            case 27 ->
                // show passengers that travel on a given train
                    showPassengersOnTrain();
            case 99 -> {
                conMan.close();
                System.exit(0);
            }
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
        System.out.print("id_pasager=");
        int id_pasager = readInt();
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
                Passenger passenger = new Passenger(id_pasager, name, firstName, email, cnp, passengerType);
                passenger2DB.add(passenger);
                id = service.addPassenger(passenger);
            } catch (RuntimeException addError) {
                System.out.println("Add error!");
            }
        } catch (RuntimeException invalidPassengerType) {
            System.out.println("Invalid passengerType!");
        }
        return id;
    }

    private void editPassenger() {
        service.listPassengers();
        System.out.println("id_pasager=");
        int id_pasager = readInt();
        String column=null;
        String value=null;
        while (true){
            System.out.println("Column to update (use column name from database):");
            column = s.nextLine();
            if (!column.equals("id_pasager")) {
                break;
            } else System.out.println("Invalid column name!");
        }
        if (column.equals("tip_pasager")) {
            while (true) {
                System.out.println("New field value:");
                value = s.nextLine();
                if (!((value.equals("ADULT")) || (value.equals("COPIL")) || (value.equals("INFANT"))|| (value.equals("STUDENT"))||(value.equals("PENSIONAR"))))
                    System.out.println("Invalid value!");
                else break;
            }
        } else {
            System.out.println("New field value:");
            value = s.nextLine();
        }
        try {
            service.updatePassenger(service.getPassengerbyDBid(id_pasager),passenger2DB.update(id_pasager,column,value));
        } catch (RuntimeException editError) {
            System.out.println("Update error!");
        }
    }

    private void deletePassenger() {
        service.listPassengers();
        System.out.println("id_pasager=");
        int id_pasager = readInt();
        try {
            passenger2DB.delete(id_pasager);
            service.deletePassenger(service.getPassengerbyDBid(id_pasager));
        } catch (RuntimeException deleteError) {
            System.out.println("Delete error!");
        }
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
        System.out.print("id_statie=");
        int id_statie = readInt();
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("address=");
        String address = s.nextLine();
        try {
            Station station = new Station(id_statie, name, address);
            station2DB.add(station);
            id = service.addStation(station);
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private void editStation() {
        service.listStations();
        System.out.println("id_statie=");
        int id_statie = readInt();
        String column = null;
        while (true) {
            System.out.println("Column to update (use column name from database):");
            column = s.nextLine();
            if (!column.equals("id_statie")) {
                break;
            } else System.out.println("Invalid column name!");
        }
        System.out.println("New field value:");
        String value = s.nextLine();
        try {
            service.updateStation(service.getStationbyDBid(id_statie), station2DB.update(id_statie, column, value));
        } catch (RuntimeException e) {
            System.out.println("Update error!");
        }
    }

    private void deleteStation() {
        service.listStations();
        System.out.println("id_statie=");
        int id_statie = readInt();
        try {
            station2DB.delete(id_statie);
            service.deleteStation(service.getStationbyDBid(id_statie));
        } catch (RuntimeException e) {
            System.out.println("Delete error!");
        }
    }

    private int addSender() {
        int id = -1;
        System.out.print("id_expeditor=");
        int id_expeditor = readInt();
        System.out.print("name=");
        String name = s.nextLine();
        System.out.print("firstName=");
        String firstName = s.nextLine();
        System.out.print("email=");
        String email = s.nextLine();
        System.out.print("CNP=");
        String cnp = s.nextLine();
        System.out.print("phoneNumber=");
        String phoneNumber = s.nextLine();
        try {
            Sender sender = new Sender(id_expeditor, name, firstName, email, cnp, phoneNumber);
            sender2DB.add(sender);
            id = service.addSender(sender);
        } catch (RuntimeException addError) {
            System.out.println("Add error!");
        }
        return id;
    }

    private void editSender() {
        service.listSenders();
        System.out.println("id_expeditor=");
        int id_expeditor = readInt();
        String column = null;
        while (true) {
            System.out.println("Column to update (use column name from database):");
            column = s.nextLine();
            if (!column.equals("id_expeditor")) {
                break;
            } else System.out.println("Invalid column name!");
        }
        System.out.println("New field value:");
        String value = s.nextLine();
        try {
            service.updateSender(service.getSenderbyDBid(id_expeditor), sender2DB.update(id_expeditor, column, value));
        } catch (RuntimeException e) {
            System.out.println("Update error!");
        }
    }

    private void deleteSender() {
        service.listSenders();
        System.out.println("id_expeditor=");
        int id_expeditor = readInt();
        try {
            sender2DB.delete(id_expeditor);
            service.deleteSender(service.getSenderbyDBid(id_expeditor));
        } catch (RuntimeException e) {
            System.out.println("Delete error!");
        }
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
        System.out.print("id_functie=");
        int id_functie = readInt();
        System.out.print("jobTitle=");
        String jobTitle = s.nextLine();
        System.out.print("jobDescription=");
        String jobDescription = s.nextLine();
        System.out.print("jobLocationType=");
        try {
            JobLocationType jobLocationType = JobLocationType.valueOf(s.nextLine());
            try {
                Job job = new Job(id_functie, jobTitle, jobDescription, jobLocationType);
                job2DB.add(job);
                id = service.addJob(job);
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

    private void editJob() {
        service.listJobs();
        System.out.print("id_functie=");
        int id_functie = readInt();
        String column = null;
        String value = null;
        while (true) {
            System.out.println("Column to update (use column name from database):");
            column = s.nextLine();
            if (!column.equals("id_functie")) {
                break;
            } else System.out.println("Invalid column name!");
        }
        if (column.equals("locatie_functie")) {
            while (true) {
                System.out.println("New field value:");
                value = s.nextLine();
                if (!((value.equals("Remote")) || (value.equals("Onsite")) || (value.equals("Hybrid"))))
                    System.out.println("Invalid value!");
                else break;
            }
        } else {
            System.out.println("New field value:");
            value = s.nextLine();
        }
        try {
            service.updateJob(service.getJobByDBid(id_functie), job2DB.update(id_functie, column, value));
        } catch (RuntimeException e) {
            System.out.println("Update error!");
        }
    }

    private void deleteJob() {
        service.listJobs();
        System.out.print("id_functie=");
        int id_functie = readInt();
        try {
            job2DB.delete(id_functie);
            service.deleteJob(service.getJobByDBid(id_functie));
        } catch (RuntimeException e) {
            System.out.println("Delete error!");
        }
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
