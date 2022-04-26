package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Job;
import com.farcasanutudorandrei.domain.JobLocationType;
import com.farcasanutudorandrei.domain.Passenger;
import com.farcasanutudorandrei.domain.PassengerType;

import java.io.*;
import java.util.ArrayList;

public class Passenger2CSV implements GenericCSVIO<Passenger> {
    private AuditService auditService = AuditService.getInstance();
    private static final Passenger2CSV instance = new Passenger2CSV();

    private Passenger2CSV() {
    }

    public static Passenger2CSV getInstance() {
        return instance;
    }

    @Override
    public void add(String filename, Passenger collection) {
        FileWriter fout = null;
        try {
            fout = new FileWriter(filename, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fout.write((collection.getName() + ',' + collection.getFirstName() + ',' + collection.getEmail() + ',' + collection.getCNP() + ',' + collection.getPassengerType() + '\n'));
            fout.close();
            auditService.add("Added Passenger");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Passenger> load(String filename) {
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Passenger> colect = new ArrayList<Passenger>();
        String entry;
        while (true) {
            try {
                if (!((entry = fin.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] tokens = entry.split(",");
            Passenger passenger = new Passenger(tokens[0], tokens[1], tokens[2], tokens[3], PassengerType.valueOf(tokens[4]));
            colect.add(passenger);
        }
        auditService.add("Loaded Passengers");
        return colect;
    }
}
