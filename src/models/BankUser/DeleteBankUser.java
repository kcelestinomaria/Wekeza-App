package models.BankUser;

import java.sql.Statement;
import models.BankUser.ViewBankUsers;

import models.DBConnection;

public class DeleteBankUser {
    
    private String user;

    void doDelete(){
        // check if the username has been supplied
        if (user != null){
            int numRows;
            try {

                // First, instantiate DBConnection class to use the connection
                DBConnection stayconnected = new DBConnection();
                stayconnected.getConnection();

                // Second, we create the statement object for executing queries
                Statement stmt = stayconnected.con.createStatement();

                // Then, execute the delete statement and assigned number of 
                // affected rows to numRows
                numRows = stmt.executeUpdate("DELETE FROM logins WHERE userName ='"+ user + "'");
                if (numRows > 0){
                    System.out.println("User " + user + " deleted successfully");
                } else {
                    System.out.println("User " + user + " was not found. Delete failed");
                }

                // Close the connection
                stayconnected.con.close();
                ViewBankUsers goToUserList = ViewBankUsers();
                goToUserList.doList()
            } catch (SQLException e) {
                System.out.println("Error! See below details \n");
                System.out.println(e);
            }
        } else {
            System.out.println("You must provide a username to delete a user");
        }
    }

    public void setBankUserDetails(String userName) {
        this.user = userName;
        doDelete();
    }
}
