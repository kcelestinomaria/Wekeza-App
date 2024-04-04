package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import models.BankUser.AddBankUser;

public class UserRegistrationModel {

    public static String registerUser(String username, String email, String password) {
        if (isEmailRegistered(email)) {
            return "User with the same credentials already exists. Please log in instead.";
        }

        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            return "User registered successfully!";
        } catch (SQLException ex) {
            return "Error registering user: " + ex.getMessage();
        }
        
    }

    //AddBankUser bankUser = new AddBankUser();
    //bankUser.setBankUserDetails(username, password);

   public static boolean isEmailRegistered(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
