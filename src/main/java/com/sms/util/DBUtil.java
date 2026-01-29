package com.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static Connection con;

    public static Connection getConnection() {

        try {
            if (con == null) {

                // Oracle Driver
                Class.forName("oracle.jdbc.OracleDriver");

                // ORCL Connection
                con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521/orcl",
                        "system",
                        "VISHNU"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
