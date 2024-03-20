package models;

import java.sql.*;

public class DBConnection {

    Connection con;
    
    Connection getConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/wekeza-app-database", "root", "");
            
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed. See error below.");
            System.out.println(e);
        }
        return con;
    }
    
}
