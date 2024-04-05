package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.DBConnection;

public class InvestmentBankAccount extends BankAccount {

    private String investmentType;
    private static final String TABLE_NAME = "investment_bank_accounts";

    // Constructor
    public InvestmentBankAccount(String investmentType) {
        super();
        this.investmentType = investmentType;
        this.accountType = "Investment Bank Account";
    }

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


    // Getters and Setters for investmentType
    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
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

   
    @Override
    public boolean saveAccountDetails() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO " + TABLE_NAME + " (account_id, account_number, balance, investment_type) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, this.accountId);
            statement.setLong(2, this.accountNumber);
            statement.setDouble(3, this.balance);
            statement.setString(4, this.investmentType);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(null, statement, connection);
        }
        return false;
    }

    public static BankAccount getInvestmentBankAccountFromResultSet(ResultSet resultSet) {
         try {
            int accountId = resultSet.getInt("account_id");
            long accountNumber = resultSet.getLong("account_number");
            double balance = resultSet.getDouble("balance");
            String investmentType = resultSet.getString("investment_type");
            InvestmentBankAccount account = new InvestmentBankAccount(investmentType);
            account.setAccountId(accountId);
            account.setAccountNumber(accountNumber);
            account.setBalance(balance);
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

  
    // Other methods specific to InvestmentBankAccount
}
