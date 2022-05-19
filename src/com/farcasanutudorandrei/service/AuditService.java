package com.farcasanutudorandrei.service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AuditService {
    private static final AuditService instance = new AuditService();

    private AuditService() {
    }

    public static AuditService getInstance() {
        return instance;
    }

    public void add(String action) {
        FileWriter fout = null;
        try {
            fout = new FileWriter("Audit.csv", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fout.write((action+','+(new Timestamp(System.currentTimeMillis()))+'\n'));
            fout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
