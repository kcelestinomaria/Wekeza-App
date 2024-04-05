package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.Transaction;

public class TransactionView extends JFrame {
    private String accountId; // Current user's account ID
    private long accountNumber; // Current user's account number

    public TransactionView(String accountId, long accountNumber) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;

        setTitle("Transaction View");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#FFFFFF")); // Set background color


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton sendTransactionButton = new JButton("Send Transaction");
        sendTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendTransaction();
            }
        });
        panel.add(sendTransactionButton);

        JButton receiveTransactionButton = new JButton("Receive Transaction");
        receiveTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiveTransaction();
            }
        });
        panel.add(receiveTransactionButton);

        JButton viewTransactionsButton = new JButton("View Transactions");
        viewTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTransactions();
            }
        });
        panel.add(viewTransactionsButton);

        add(panel);
        setVisible(true);
    }

    private void sendTransaction() {
        String recipientAccountId = JOptionPane.showInputDialog("Enter Recipient Account ID:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter Amount:"));

        Transaction transaction = new Transaction(Integer.parseInt(accountId), accountNumber, Integer.parseInt(recipientAccountId), 0, amount);
        boolean success = transaction.saveTransactionDetails();

        if (success) {
            JOptionPane.showMessageDialog(this, "Transaction sent successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Transaction sending failed!");
        }
    }

    private void receiveTransaction() {
        // Generate the user's account ID and account number
        JOptionPane.showMessageDialog(this, "Your Account ID: " + accountId + "\nYour Account Number: " + accountNumber +
                "\nShare this information with the person sending you funds.");
    }

    private void viewTransactions() {
        List<Transaction> transactions = Transaction.getAllTransactions();

        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No transactions found!");
            return;
        }

        StringBuilder message = new StringBuilder("Transactions:\n");
        for (Transaction transaction : transactions) {
            message.append("Sender Account ID: ").append(transaction.getSenderAccountId()).append(", ");
            message.append("Recipient Account ID: ").append(transaction.getReceiverAccountId()).append(", ");
            message.append("Amount: ").append(transaction.getAmount()).append(", ");
            message.append("Timestamp: ").append(transaction.getTimestamp()).append("\n");
        }

        JOptionPane.showMessageDialog(this, message.toString());
    }

    public static void main(String[] args) {
        // For testing purposes, let's assume the user's account ID is "1234" and account number is "5678"
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TransactionView("1234", 5678);
            }
        });
    }
}
