package views;

import javax.swing.*;

import models.BankAccount.BankAccount;
import models.BankAccount.StandardBankAccount;
import models.BankAccount.InvestmentBankAccount;
import models.BankAccount.SavingsBankAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountsView {
    
    private static final String EXIT_ON_CLOSE = null;
    private List<BankAccount> accounts;

    public AccountsView() {
        accounts = new ArrayList<>();

        setTitle("My Bank Accounts");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton createStandardBankAccountButton = new JButton("Create A Standard Bank Account");
        createStandardBankAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount(new StandardBankAccount(0, 0, 0, 0));
            }
        });
        panel.add(createStandardBankAccountButton);

        JButton createSavingsBankAccountButton = new JButton("Create A Savings Account");
        createSavingsBankAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount(new SavingsBankAccount());
            }
        });
        panel.add(createSavingsBankAccountButton);

        JButton createInvestmentButton = new JButton("Create An Investment Account");
        createInvestmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount(new InvestmentBankAccount());
            }
        });
        panel.add(createInvestmentButton);

        JButton viewAccountsButton = new JButton("View Accounts");
        viewAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAccounts();
            }
        });
        panel.add(viewAccountsButton);

        add(panel);
        setVisible(true);
    }

    private void setDefaultCloseOperation(String exitOnClose) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDefaultCloseOperation'");
    }

    private void setSize(int i, int j) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSize'");
    }

    private void setTitle(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTitle'");
    }

    private void createAccount(BankAccount account) {
        //accounts.add(account);

        // Generate random values for accountId and accountNumber
        Random random = new Random();
        int accountId = random.nextInt(1000) + 1000; // Random 4-digit number
        long accountNumber = Math.abs(random.nextLong()); // Random positive long number

        // Set default starting balance and other variables
        account.setAccountId(accountId);
        account.setAccountNumber(accountNumber);
        account.deposit(10.0); // Starting balance of $10

        accounts.add(account);
        JOptionPane.showMessageDialog(this, "Account created successfully!\nAccount ID: " + accountId +
                "\nAccount Number: " + accountNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewAccounts() {
        StringBuilder message = new StringBuilder("Accounts:\n");
        for (BankAccount account : accounts) {
            message.append(account.getAccountType()).append(": $").append(account.getBalance())
                    .append(" (ID: ").append(account.getAccountId()).append(", Number: ")
                    .append(account.getAccountNumber()).append(")\n");
        }
        JOptionPane.showMessageDialog(this, message.toString(), "Accounts", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AccountsView();
            }
        });
    }
}
