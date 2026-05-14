package com.digitalloker;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/digital_locker",
                "root",
                "Palak@2608*#123"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}