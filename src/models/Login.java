package models;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/wekeza-db";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static String authenticate(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Check if the user exists in the database
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                if ("admin".equals(role)) {
                    return "Admin login successful";
                } else {
                    return "User login successful";
                }
            } else {
                // If the user does not exist, prompt them to register
                return "User not found. Please register.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Failed to login. Please try again later.";
        }
    }
}
