package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DBConnection;

public class StandardBankAccount extends BankAccount {

    // Additional variables specific to StandardBankAccount
    private static final String TABLE_NAME = "standard_bank_account";
    private double overdraftLimit;

    // Constructors
    public StandardBankAccount() {
        super();
        this.overdraftLimit = overdraftLimit;
    }

    // Getters and Setters for overdraftLimit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Retrieve a standard account from the database based on account ID
    public static StandardBankAccount getStandardBankAccountById(int accountId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long accountNumber = resultSet.getLong("account_number");
                double balance = resultSet.getDouble("balance");
                double overdraftLimit = resultSet.getDouble("overdraft_limit");
                return new StandardBankAccount();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return null;
    }

    // Update the balance of the standard account in the database
    @Override
    public void updateBalance(double newBalance) {
        super.setBalance(newBalance);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "UPDATE " + TABLE_NAME + " SET balance = ? WHERE account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, newBalance);
            statement.setInt(2, (int) getAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
    }

    // Close resources (result set, statement, connection)
    protected static void closeResources(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Other methods specific to StandardBankAccount
    @Override
    public void deposit(double amount) {
        double currentBalance = (double) getBalance();
        updateBalance(currentBalance + amount);
    }

    @Override
    public boolean withdraw(double amount) {
        double currentBalance = (double) getBalance();
        if (currentBalance - amount >= -overdraftLimit) {
            updateBalance(currentBalance - amount);
            return true;
        }
        return false;
    }

    @Override
    public void setAccountId(int accountId) {
        // Not needed for this implementation
    }

    @Override
    public void setAccountNumber(long accountNumber) {
        // Not needed for this implementation
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public int getAccountId() {
        return accountId;
    }

    @Override
    public long getAccountNumber() {
        return accountNumber;
    }
}
