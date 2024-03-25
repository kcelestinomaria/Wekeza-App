package models.BankUser;

import java.sql.*;

import javax.swing.text.View;

import models.DBConnection;

public class UpdateBankUser {
    
    private String userHere, pass;

    ViewBankUsers goToUserList = new ViewBankUsers();

    void doUpdate() {

        // check if username is supplied
        if(userHere != null) {

            int numRows;
            try {

                // instantiate the DBConnection class to use the connection
                DBConnection stayconnected = new DBConnection();
                DBConnection.getConnection();

                // Create the statement object for executing queries
                Statement stmt =  stayconnected.con.createStatement();

                // Execute the delete statement and assigned number of affected rows
                numRows = stmt.executeUpdate("UPDATE logins SET password ='" + pass + "'WHERE userName ='" + userHere + "'" );
                if (numRows > 0) {
                    System.out.println("Password for"+userHere+" changed successfully");
                } else {
                    System.out.println("Password change failed. Try again");
                    goToUserList.doList();
                }
                // Close the connection
                stayconnected.con.close();
                goToUserList.doList();
            } catch (SQLException e) {
                System.out.println("Error! See below details \n");
                System.out.println(e);
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
