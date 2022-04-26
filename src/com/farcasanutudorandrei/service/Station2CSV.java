package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Job;
import com.farcasanutudorandrei.domain.JobLocationType;
import com.farcasanutudorandrei.domain.Station;

import java.io.*;
import java.util.ArrayList;

public class Station2CSV implements GenericCSVIO<Station>{
    private AuditService auditService = AuditService.getInstance();
    private static final Station2CSV instance = new Station2CSV();

    private Station2CSV() {
    }

    public static Station2CSV getInstance() {
        return instance;
    }
    @Override
    public void add(String filename, Station collection) {
        FileWriter fout = null;
        try {
            fout = new FileWriter(filename, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fout.write((collection.getName() + ',' + collection.getAddress() + '\n'));
            fout.close();
            auditService.add("Added Station");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Station> load(String filename) {
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Station> colect = new ArrayList<Station>();
        String entry;
        while (true) {
            try {
                if (!((entry = fin.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] tokens = entry.split(",");
            Station station = new Station(tokens[0], tokens[1]);
            colect.add(station);
        }
        auditService.add("Loaded Stations");
        return colect;
    }
}
