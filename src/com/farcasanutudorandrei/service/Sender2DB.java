package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Sender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sender2DB implements GenericDBIO<Sender>{
    private AuditService auditService = AuditService.getInstance();
    private ConnectionManager conMan= ConnectionManager.getInstance();
    private Service service = Service.getInstance();
    private static final Sender2DB instance = new Sender2DB();

    private Sender2DB() {
    }
    public static Sender2DB getInstance() {
        return instance;
    }
    @Override
    public void add(Sender collection) {
        PreparedStatement stmt = conMan.ppSt("insert into expeditori values(?,?,?,?,?,?)");
        try {
            stmt.setInt(1,collection.getId_expeditor());
            stmt.setString(2,collection.getFirstName());
            stmt.setString(3,collection.getName());
            stmt.setString(4,collection.getPhoneNumber());
            stmt.setString(5,collection.getCNP());
            stmt.setString(6,collection.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.execute();
            auditService.add("Added Sender to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        try {
            ResultSet rs = conMan.ppSt("select * from expeditori").executeQuery();
            while(rs.next()){
                service.addSender(new Sender(rs.getInt("id_expeditor"), rs.getString("nume_expeditor"), rs.getString("prenume_expeditor"), rs.getString("email"), rs.getString("CNP"), rs.getString("telefon_expeditor")));
            }
            auditService.add("Loaded Senders from database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
