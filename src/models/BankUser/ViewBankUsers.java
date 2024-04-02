package models.BankUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.DBConnection;

public class ViewBankUsers<BankUser> {
    public List<BankUser> getUsers() {
        List<BankUser> users = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM logins")) {
            while (resultSet.next()) {
                String userName = resultSet.getString("userName");
                int roleID = resultSet.getInt("roleID");
                users.add(new BankUser(userName, roleID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
