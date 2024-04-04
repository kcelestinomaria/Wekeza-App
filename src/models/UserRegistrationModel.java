package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import models.BankUser.AddBankUser;

public class UserRegistrationModel {

    public static String registerUser(String username, String email, String password) {
        if (userExists(username, email)) {
            return "User with the same username or email already exists. Please log in instead.";
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

    private static boolean userExists(String username, String email) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? OR email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If result set has next row, user exists
        } catch (SQLException ex) {
            return false;
        }
    }
}
