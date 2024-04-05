package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

//import models.DBConnection;

public abstract class BankAccount {

    protected int accountId;
    protected long accountNumber;
    protected double balance;
    protected String accountType;

    // Generate random values for accountId and accountNumber
    protected Random random = new Random();

    // Constructor
    protected BankAccount() {
        this.accountId = random.nextInt(1000) + 1000;
        this.accountNumber = Math.abs(random.nextLong());
        this.balance = 10.0; // starting balance - 10.0 $
        this.accountType = "";
    }

    // Getters and Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Abstract methods
    public abstract void updateBalance(double newBalance);

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);

    public abstract boolean deleteAccount();

    public abstract boolean saveAccountDetails();

    /*
    // Additional methods
   // Static method to get account by ID (common for all subclasses)
    public static BankAccount getAccountById(int accountId, String tableName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM " + tableName + " WHERE account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Create the appropriate subclass instance based on the account type
                String accountType = resultSet.getString("account_type");
                switch (accountType) {
                    case "Standard Bank Account":
                        return StandardBankAccount.getStandardBankAccountFromResultSet(resultSet);
                    case "Savings Bank Account":
                        return SavingsBankAccount.getSavingsBankAccountFromResultSet(resultSet);
                    case "Investment Bank Account":
                        return InvestmentBankAccount.getInvestmentBankAccountFromResultSet(resultSet);
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return null;
    }
    */

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

    public String getAccountType() {
        return accountType;
    }
}
