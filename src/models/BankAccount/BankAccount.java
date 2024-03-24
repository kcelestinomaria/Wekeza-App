package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DBConnection;

public abstract class BankAccount {
    
    private int accountId;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(int accountId, String accountNumber, double balance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters, & Setters

    // Abstract methods for common functionality e.g deposit & withdraw
    public abstract void updateBalance(double newBalance);

    // Additional methods for account functionality

    // Deposit funds into the account
    public void deposit(double amount) {
        balance += amount;
        updateBalance(balance);
    }

    // Withdraw funds from the account
    public boolean withdraw(double amount) {

        if (amount <= balance) {
            balance -= amount;
            updateBalance(balance);
            return true; // 
        } else {
            return false; // Insufficient funds
        }
    }

    // Get account details from the database based on account ID
    public static BankAccount getAccountById(int accountId, String tableName) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            con = DBConnection.getConnection();
            String query = "SELECT * FROM " + tableName + " WHERE account_id = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String accountNumber = resultSet.getString("account_number");
                double balance = resultSet.getDouble("balance");
                // Create and return the appropriate account object based on the table name
                if (tableName.equals("standard_accounts")) {
                    double overdraftLimit = resultSet.getDouble("overdraft_limit");
                    return new StandardAccount(accountId, accountNumber, balance, overdraftLimit);
                } else if (tableName.equals("savings_accounts")) {
                    double interestRate = resultSet.getDouble("interest_rate");
                    return new SavingsAccount(accountId, accountNumber, balance, interestRate);
                } else if (tableName.equals("investment_accounts")) {
                    String investmentType = resultSet.getString("investment_type");
                    return new InvestmentAccount(accountId, accountNumber, balance, investmentType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, con);
        }
        return null; // Account not found
    }

    // Close resources(result set, statement, connection)
    protected static void closeResources(ResultSet resultSet, PreparedStatement statement, Connection con) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBalance(double newBalance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBalance'");
    }

}
