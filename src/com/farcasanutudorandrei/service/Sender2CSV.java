package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Passenger;
import com.farcasanutudorandrei.domain.PassengerType;
import com.farcasanutudorandrei.domain.Sender;

import java.io.*;
import java.util.ArrayList;

public class Sender2CSV implements GenericCSVIO<Sender> {
    private Service service = new Service();
    private AuditService auditService = AuditService.getInstance();
    private static final Sender2CSV instance = new Sender2CSV();

    private Sender2CSV() {
    }

    public static Sender2CSV getInstance() {
        return instance;
    }
    @Override
    public void add(String filename, Sender collection) {
        FileWriter fout = null;
        try {
            fout = new FileWriter(filename, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fout.write((collection.getName() + ',' + collection.getFirstName() + ',' + collection.getEmail() + ',' + collection.getCNP() + ',' + collection.getPhoneNumber() + '\n'));
            fout.close();
            auditService.add("Added Sender");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Sender> load(String filename) {
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Sender> colect = new ArrayList<Sender>();
        String entry;
        while (true) {
            try {
                if (!((entry = fin.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] tokens = entry.split(",");
            Sender sender = new Sender(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            colect.add(sender);
        }
        auditService.add("Loaded Senders");
        return colect;
    }
}
