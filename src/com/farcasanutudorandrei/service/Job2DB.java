package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Job;
import com.farcasanutudorandrei.domain.JobLocationType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Job2DB implements GenericDBIO<Job> {
    private AuditService auditService = AuditService.getInstance();
    private ConnectionManager conMan = ConnectionManager.getInstance();
    private Service service = Service.getInstance();
    private static final Job2DB instance = new Job2DB();

    private Job2DB() {
    }

    public static Job2DB getInstance() {
        return instance;
    }

    @Override
    public void add(Job collection) {
        PreparedStatement stmt = conMan.ppSt("insert into functii values(?,?,?,?)");
        try {
            stmt.setInt(1, collection.getId_job());
            stmt.setString(2, collection.getJobTitle());
            stmt.setString(3, collection.getJobDescription());
            stmt.setString(4, collection.getJobLocationType().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.execute();
            auditService.add("Added Job to database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error adding Job to database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        try {
            ResultSet rs = conMan.ppSt("select * from functii").executeQuery();
            while (rs.next()) {
                service.addJob(new Job(rs.getInt("id_functie"), rs.getString("nume_functie"), rs.getString("descriere_functie"), JobLocationType.valueOf(rs.getString("locatie_functie"))));
            }
            auditService.add("Loaded Jobs from database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error loading Jobs from database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job update(int id,String column,String value) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
