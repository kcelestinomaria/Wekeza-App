package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private static final String QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";
    private static final String ADMIN_USERNAME = "Celestino";
    private static final String ADMIN_PASSWORD = "Celestino@Strathmore";
    private static final String ADMIN_ROLE = "Admin";

    public String authenticate(String username, String password) {
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            return ADMIN_ROLE; // Admin credentials match
        }

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    if (ADMIN_ROLE.equals(role)) {
                        return ADMIN_ROLE;
                    } else {
                        return "User";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User"; // Default to "User" for normal users
    }
}
