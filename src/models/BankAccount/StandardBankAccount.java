package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DBConnection;

public class StandardBankAccount extends BankAccount {

    private static final String TABLE_NAME = "standard_bank_account";
    private double overdraftLimit;

    public StandardBankAccount() {
        super();
        this.accountType = "A Standard(Checking) Bank Account";
        this.overdraftLimit = 10000.9998;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

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
                StandardBankAccount account = new StandardBankAccount();
                account.setAccountId(accountId);
                account.setAccountNumber(accountNumber);
                account.setBalance(balance);
                account.setOverdraftLimit(overdraftLimit);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return null;
    }

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
            statement.setInt(2, getAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
    }

    @Override
    public boolean deleteAccount() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "DELETE FROM " + TABLE_NAME + " WHERE account_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, getAccountId());
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
        return false;
    }

    @Override
    public boolean saveAccountDetails() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO " + TABLE_NAME
                    + " (account_id, account_number, balance, overdraft_limit) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, getAccountId());
            statement.setLong(2, getAccountNumber());
            statement.setDouble(3, getBalance());
            statement.setDouble(4, getOverdraftLimit());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
        return false;
    }

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


    @Override
    public void deposit(double amount) {
        double currentBalance = getBalance();
        double newBalance = currentBalance + amount;
        updateBalance(newBalance);
        updateBalanceInDatabase(newBalance);
    }

    @Override
    public boolean withdraw(double amount) {
        double currentBalance = getBalance();
        if (currentBalance - amount >= -overdraftLimit) {
            double newBalance = currentBalance - amount;
            updateBalance(newBalance);
            updateBalanceInDatabase(newBalance);
            return true;
        }
        return false;
    }

    // Other methods specific to StandardBankAccount
}
