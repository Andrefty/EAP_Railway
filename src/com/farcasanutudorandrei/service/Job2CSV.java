package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Job;
import com.farcasanutudorandrei.domain.JobLocationType;

import java.io.*;
import java.util.ArrayList;

public class Job2CSV implements GenericCSVIO<Job> {
    private Service service = new Service();
    private AuditService auditService=AuditService.getInstance();
    private static final Job2CSV instance = new Job2CSV();

    private Job2CSV() {
    }

    public static Job2CSV getInstance() {
        return instance;
    }

    @Override
    public void add(String filename, Job collection) {
        FileWriter fout = null;
        try {
            fout = new FileWriter(filename, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fout.write((collection.getJobTitle() + ',' + collection.getJobDescription() + ',' + collection.getJobLocationType() + '\n'));
            fout.close();
            auditService.add("Added Job");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Job> load(String filename) {
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Job> colect = new ArrayList<Job>();
        String entry;
        while (true) {
            try {
                if (!((entry = fin.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] tokens = entry.split(",");
            Job job = new Job(tokens[0], tokens[1], JobLocationType.valueOf(tokens[2]));
            colect.add(job);
        }
        auditService.add("Loaded Jobs");
        return colect;
    }
//Pentru testul din main    public void testarestatic(){
//        service.addJob(new Job("adugare de stest","descriedere de test", JobLocationType.valueOf("Hybrid")));
//    }

}
