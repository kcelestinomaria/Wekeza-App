package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

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

    public abstract void readAccountDetails();

    public abstract boolean saveAccountDetails();

    // Additional methods
    public static BankAccount getAccountById(int accountId, String tableName) {
        // Implementation in child classes
        return null;
    }

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
