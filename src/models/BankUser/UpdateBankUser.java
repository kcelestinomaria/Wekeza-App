package models.BankUser;

//import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.DBConnection;

public class UpdateBankUser {

    private String userHere, pass;

    public void doUpdate() {
        // check if username is supplied
        if (userHere != null) {
            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE logins SET password = ? WHERE userName = ?")) {
                statement.setString(1, pass);
                statement.setString(2, userHere);
                int numRows = statement.executeUpdate();
                if (numRows > 0) {
                    System.out.println("Password for " + userHere + " changed successfully");
                } else {
                    System.out.println("Password change failed. Try again");
                }
            } catch (SQLException e) {
                System.out.println("Error! See below details \n");
                e.printStackTrace();
            }
        } else {
            System.out.println("You must provide a username to update user details");
        }
    }

    public void setBankUserDetails(String user, String pass) {
        this.userHere = user;
        this.pass = pass;
        doUpdate();
    }
}
