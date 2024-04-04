package models.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import models.DBConnection;

public abstract class BankAccount {
    
    protected int accountId;
    protected long accountNumber;
    protected double balance;

    // Generate random values for accountId and accountNumber
    Random random = new Random();
    
    public BankAccount(int accountId, String accountNumber, double balance) {
        this.accountId = random.nextInt(1000) + 1000;
        this.accountNumber = Math.abs(random.nextLong());
        this.balance = 10.0; // starting balance - 10.0 $
    }

    // Getters, & Setters

    // Abstract methods for common functionality e.g deposit & withdraw
    public abstract void updateBalance(double newBalance);

    // Additional methods for account functionality

    // Deposit funds into the account
    public abstract void deposit(double amount);

    // Withdraw funds from the account
    public abstract boolean withdraw(double amount);


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
                    return new StandardBankAccount(accountId, accountNumber, balance, overdraftLimit);
                } else if (tableName.equals("savings_accounts")) {
                    double interestRate = resultSet.getDouble("interest_rate");
                    return new SavingsBankAccount(accountId, accountNumber, balance, interestRate);
                } else if (tableName.equals("investment_accounts")) {
                    String investmentType = resultSet.getString("investment_type");
                    return new InvestmentBankAccount(accountId, accountNumber, balance, investmentType);
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

    public abstract void setAccountId(int accountId2);

    public abstract void setAccountNumber(long accountNumber2);

    public Object getAccountType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountType'");
    }

    protected abstract Object getBalance();

    protected abstract Object getAccountId();

    protected abstract Object getAccountNumber();

}
