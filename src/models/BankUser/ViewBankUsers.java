package models.BankUser;

//import static models.BankUser.Home.main; // formerly User.Home.main

import static models.BankUser;
import static Home.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import models.DBConnection;

/**
 *
 * @author celestino
 */
public class ViewBankUsers {

    private String userHere, passHere;
    int choiceHere;

    void doList() {
        //Check if username or password supplied
        try {
            //Instantiate DbConnection class to use the connection
            DBConnection stayconnected = new DBConnection();
            stayconnected.getConnection();
            //Create the statement object for executing queries
            Statement stmt = stayconnected.con.createStatement();
            //Execute the statement
            ResultSet rs = stmt.executeQuery("SELECT * FROM logins");
            //Handle the results set
            System.out.format("%s, %s\n", "User", "Role ID");
            while (rs.next()) {
                //System.out.println("User "+userNumber);
                System.out.format("%s, %s\n", rs.getString("userName"), rs.getInt("roleID"));
            }
            //Close the connection
            stayconnected.con.close();
            beginAfterView();
        } catch (SQLException e) {
            System.out.println("Error! See below details \n");
            System.out.println(e);
        }
    }

    void beginAfterView() {
        //Ask the user what to do
        Scanner sc = new Scanner(System.in);
        System.out.println("Do more: ");
        System.out.println("Enter 1 to Update user details.");
        System.out.println("Enter 2 to Delete a user.");
        System.out.println("Enter 3 to Logout a user.");
        choiceHere = sc.nextInt();
        sc.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        if (choiceHere == 1) {
            System.out.println("...Update User...");
            System.out.println("Enter username to select a user: ");
            userHere = sc.nextLine();
            System.out.println("Enter a new password for user "+userHere+"");
            passHere = sc.nextLine();
            //Jump to UpdateBankUser.java
            UpdateBankUser UpdateBankUserObject = new UpdateBankUser();
            UpdateBankUserObject.setBankUserDetails(userHere, passHere);
        } else if (choiceHere == 2) {
            System.out.println("...Remove a User...");
            System.out.println("Please enter the username to remove: ");
            userHere = sc.nextLine();
            //Jump to DeleteBankUser.java
            DeleteBankUser deleteBankUserobject = new DeleteBankUser();
            deleteBankUserobject.setBankUserDetails(userHere);
        } else if (choiceHere == 3) {
            System.out.println("You are now logged out...");
            main(null);
        } else {
            System.out.println("Invalid choice");
            beginAfterView();
        }
    }
}
