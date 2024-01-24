package com.example.codeE.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    Connection connection;
    private static final String JDBC_URL = "jdbc:mysql://codee-database-server.cghma3k79fds.ap-southeast-1.rds.amazonaws.com:3306/codee";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "Wisdomworks.33";

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error during database connection: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
