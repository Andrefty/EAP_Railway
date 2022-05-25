package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Station;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Station2DB implements GenericDBIO<Station>{
    private AuditService auditService = AuditService.getInstance();
    private ConnectionManager conMan= ConnectionManager.getInstance();
    private Service service = Service.getInstance();
    private static final Station2DB instance = new Station2DB();

    private Station2DB() {
    }

    public static Station2DB getInstance() {
        return instance;
    }
    @Override
    public void add(Station collection) {
        PreparedStatement stmt = conMan.ppSt("insert into statii values(?,?,?)");
        try {
            stmt.setInt(1, collection.getId_station());
            stmt.setString(2, collection.getName());
            stmt.setString(3, collection.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.execute();
            auditService.add("Added Station to database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error adding Station to database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        try {
            ResultSet rs = conMan.ppSt("select * from statii").executeQuery();
            while(rs.next()){
                service.addStation(new Station(rs.getInt("id_statie"), rs.getString("nume_statie"), rs.getString("adresa")));
            }
            auditService.add("Loaded Stations from database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error adding Station to database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
