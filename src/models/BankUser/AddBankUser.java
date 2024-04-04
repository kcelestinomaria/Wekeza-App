/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.BankUser;

import java.sql.SQLException;

import models.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBankUser {
    public static void addBankUser(String username) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO bank_users (username) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
            System.out.println("Bank user added successfully!");
        } catch (SQLException ex) {
            System.out.println("Error adding bank user: " + ex.getMessage());
        }
    }
}
