package com.farcasanutudorandrei.service;

import com.farcasanutudorandrei.domain.Passenger;
import com.farcasanutudorandrei.domain.PassengerType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passenger2DB implements GenericDBIO<Passenger>{

    private AuditService auditService = AuditService.getInstance();
    private ConnectionManager conMan= ConnectionManager.getInstance();
    private Service service = Service.getInstance();
    private static final Passenger2DB instance = new Passenger2DB();
    private Passenger2DB() {
    }
    public static Passenger2DB getInstance() {
        return instance;
    }

    @Override
    public void add(Passenger collection) {
        PreparedStatement stmt = conMan.ppSt("insert into pasageri values(?,?,?,?,?,?)");
        try {
            stmt.setInt(1, collection.getId_pasager());
            stmt.setString(2, collection.getFirstName());
            stmt.setString(3, collection.getName());
            stmt.setString(4, collection.getCNP());
            stmt.setString(5, collection.getEmail());
            stmt.setString(6, collection.getPassengerType().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.execute();
            auditService.add("Added Passenger to database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error adding Passenger to database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        try {
            ResultSet rs = conMan.ppSt("select * from pasageri").executeQuery();
            while(rs.next()){
                service.addPassenger(new Passenger(rs.getInt("id_pasager"), rs.getString("nume_pasager"), rs.getString("prenume_pasager"),  rs.getString("email"), rs.getString("CNP"), PassengerType.valueOf(rs.getString("tip_pasager"))));
            }
            auditService.add("Loaded Passengers from database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error loading Passengers from database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passenger update(int id,String column,String value) {
        PreparedStatement stmt=conMan.ppSt("select * from pasageri where id_pasager = ?");
        ResultSet rs=null;
        Passenger passenger=null;
        try {
            stmt.setInt(1,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            rs= stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());
            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error finding Passenger in database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            rs.first();
            rs.updateString(column,value);
            rs.updateRow();
            passenger=new Passenger(rs.getInt("id_pasager"), rs.getString("nume_pasager"), rs.getString("prenume_pasager"),  rs.getString("email"), rs.getString("CNP"), PassengerType.valueOf(rs.getString("tip_pasager")));
            auditService.add("Updated column "+column +" of Passenger "+ id+ " in database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error updating column "+column +" of Passenger "+ id+ " in database! "+" Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return passenger;
    }

    @Override
    public void delete(int id) {
        PreparedStatement stmt=conMan.ppSt("delete from pasageri where id_pasager = ?");
        try {
            stmt.setInt(1,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            stmt.execute();
            auditService.add("Deleted Passenger "+ id+ " from database");
        } catch (SQLException e) {
            System.out.println("SQLState: " +
                    e.getSQLState());

            System.out.println("Error Code: " +
                    e.getErrorCode());

            System.out.println("Message: " + e.getMessage());
            auditService.add("Error deleting Passenger "+ id+ " from database! Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
