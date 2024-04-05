package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int id;
    private int senderAccountId;
    private long senderAccountNumber;
    private int receiverAccountId;
    private long receiverAccountNumber;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int senderAccountId, long senderAccountNumber, int receiverAccountId, long receiverAccountNumber, double amount) {
        this.senderAccountId = senderAccountId;
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountId = receiverAccountId;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getSenderAccountId() {
        return senderAccountId;
    }

    public long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean saveTransactionDetails() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO transactions (sender_account_id, sender_account_number, receiver_account_id, receiver_account_number, amount, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, senderAccountId);
            statement.setLong(2, senderAccountNumber);
            statement.setInt(3, receiverAccountId);
            statement.setLong(4, receiverAccountNumber);
            statement.setDouble(5, amount);
            statement.setObject(6, timestamp);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(null, statement, connection);
        }
        return false;
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM transactions";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int senderAccountId = resultSet.getInt("sender_account_id");
                long senderAccountNumber = resultSet.getLong("sender_account_number");
                int receiverAccountId = resultSet.getInt("receiver_account_id");
                long receiverAccountNumber = resultSet.getLong("receiver_account_number");
                double amount = resultSet.getDouble("amount");
                LocalDateTime timestamp = resultSet.getObject("timestamp", LocalDateTime.class);
                Transaction transaction = new Transaction(senderAccountId, senderAccountNumber, receiverAccountId, receiverAccountNumber, amount);
                transaction.id = id;
                transaction.timestamp = timestamp;
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResources(resultSet, statement, connection);
        }
        return transactions;
    }

    public boolean deleteTransactionDetails() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            String query = "DELETE FROM transactions WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
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
