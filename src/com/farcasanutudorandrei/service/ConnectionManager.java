package com.farcasanutudorandrei.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

    private static final ConnectionManager instance = new ConnectionManager();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    private Connection conn;

    private AuditService auditService=AuditService.getInstance();
    private String prevUrl;
    private String prevUser;
    private String prevPass;

    public void setConnection(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            conn = DriverManager.getConnection(url, user, pass);
            prevUrl = url;
            prevUser = user;
            prevPass = pass;
            auditService.add("Connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement ppSt (String stmt) {
        try {
            if (conn.isClosed()) {
                conn = DriverManager.getConnection(prevUrl, prevUser, prevPass);
                auditService.add("Reconnected to database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return conn.prepareStatement(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            conn.close();
            auditService.add("Connection to database closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
