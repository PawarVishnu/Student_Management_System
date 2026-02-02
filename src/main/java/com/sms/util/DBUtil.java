package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // Database details
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "system"; // SQL Developer password

    // Get DB Connection
    public static Connection getConnection() {

        Connection con = null;

        try {
            // Load Oracle Driver
            Class.forName(DRIVER);

            // Create Connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Oracle JDBC Driver not found");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Database connection failed");
            e.printStackTrace();
        }

        return con;
    }

    // Test Connection
    public static void main(String[] args) {

        Connection con = getConnection();

        if (con != null) {
            System.out.println("✅ ORACLE DATABASE CONNECTED SUCCESSFULLY");
        } else {
            System.out.println("❌ DATABASE CONNECTION FAILED");
        }
    }
}
