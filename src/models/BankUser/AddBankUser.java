/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.BankUser;

import java.sql.SQLException;
import java.sql.Statement;

import models.DBConnection;

/**
 *
 * @author celestino
 */
public final class AddBankUser {

    private String user, pass;
    int roleID = 1;

    void doAdd() {
        //Check if username or password supplied
        if (user != null || pass!=null) {
   
            try {
                //Instantiate DbConnection class to use the connection
                DBConnection stayconnected = new DBConnection();
                DBConnection.getConnection();
                //Create the statement object for executing queries
                Statement stmt = stayconnected.con.createStatement();
                //Execute the statement
                stmt.executeUpdate("INSERT INTO logins VALUES('" + user + "','" + pass + "','" + roleID + "')");
                System.out.println("User details added successfully \n");
                //Close the connection
                stayconnected.con.close();
            } catch (SQLException e) {
                System.out.println("Error! See below details \n");
                System.out.println(e);
            }
        } else {
            System.out.println("Username or password can not be blank");
        }
    }

    public void setBankUserDetails(String userName, String password) {
        this.user = userName;
        this.pass = password;
        doAdd();
    }

}
