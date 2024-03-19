package models.BankUser;

import java.sql.*;

import javax.swing.text.View;

public class UpdateBankUser {
    
    private String userHere, pass;

    ViewBankUsers goToUserList = new ViewBankUsers();

    void doUpdate() {

        // check if username is supplied
        if(userHere != null) {

            int numRows;
            try {

                // instantiate the DBConnection class to use the connection
                DbConnection stayconnected = new DbConnection();
                stayconnected.getConnection();

                // Create the statement object for executing queries
                Statement stmt = stayconnected.con.createStatement();

                // Execute the delete statement and assigned number of affected rows
                numRows = stmt.executeUpdate("UPDATE logins SET password ='" + pass + )
            }
        }
    }
}
