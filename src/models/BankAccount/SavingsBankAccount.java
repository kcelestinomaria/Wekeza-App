package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DBConnection;

public class SavingsBankAccount extends BankAccount {

    private static final String TABLE_NAME = "savings_accounts";
    private double interestRate;

    // Constructors
    public SavingsBankAccount() {
        super();
        this.interestRate = 9.99;
        this.accountType = "Savings Bank Account";
    }

    // CRUD operations

    @Override
    public void updateBalance(double newBalance) {
        this.balance = newBalance;
        updateBalanceInDatabase(newBalance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        updateBalanceInDatabase(balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            updateBalanceInDatabase(balance);
            return true;
        }
        return false;
    }

    // Getters and Setters for interestRate
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Database operations

    private void updateBalanceInDatabase(double newBalance) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "UPDATE " + TABLE_NAME + " SET balance = ? WHERE account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, newBalance);
            statement.setInt(2, this.accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(null, statement, connection);
        }
    }

    public static SavingsBankAccount getSavingsAccountById(int accountId) {
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
                SavingsBankAccount account = new SavingsBankAccount();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setAccountNumber(resultSet.getLong("account_number"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setInterestRate(resultSet.getDouble("interest_rate"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(resultSet, statement, connection);
        }
        return null;
    }

    @Override
    public boolean saveAccountDetails() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " (account_number, balance, interest_rate) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setLong(1, this.accountNumber);
            statement.setDouble(2, this.balance);
            statement.setDouble(3, this.interestRate);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(null, statement, connection);
        }
        return false;
    }

    @Override
    public boolean deleteAccount() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, this.accountId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(null, statement, connection);
        }
        return false;
    }

   

}
