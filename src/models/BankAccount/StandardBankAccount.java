package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.DBConnection;

public class StandardBankAccount extends BankAccount {

    // Additional variables specific to StandardBankAccount
    private static final String TABLE_NAME = "standard_bank_account";
    private double overdraftLimit;

    // Additional methods specific to StandardBankAccount
    public StandardBankAccount(int accountId, String accountNumber, double balance, double overdraftLimit) {
        super(accountId, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Getters & Setters for overdraftLimit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public static StandardBankAccount createStandardBankAccount(String accountNumber, double initialBalance,
            double overdraftLimit) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " (account_number, balance, overdraft_limit) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, accountNumber);
            statement.setDouble(2, initialBalance);
            statement.setDouble(3, overdraftLimit);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int accountId = resultSet.getInt(1);
                return new StandardBankAccount(accountId, accountNumber, initialBalance, overdraftLimit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(resultSet, statement, connection);
        }
        return null;
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
                String accountNumber = resultSet.getString("account_number");
                double balance = resultSet.getDouble("balance");
                double overdraftLimit = resultSet.getDouble("overdraft_limit");
                return new StandardBankAccount(accountId, accountNumber, balance, overdraftLimit);
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
            statement.setInt(2, getBankAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, statement, connection);
        }
    }

    // Additional methods specific to StandardAccount

    private int getBankAccountId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBankAccountId'");
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
}
