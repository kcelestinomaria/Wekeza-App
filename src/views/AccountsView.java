package views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.BankAccount.BankAccount;
import models.BankAccount.InvestmentBankAccount;
import models.BankAccount.SavingsBankAccount;
import models.BankAccount.StandardBankAccount;

public class AccountsView extends JFrame {

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
                createAccount(new StandardBankAccount());
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

        // Combo box for selecting investment type
        String[] investmentTypes = {"Digital Assets", "Equity", "Government Bond", "Commodities"};
        JComboBox<String> investmentTypeComboBox = new JComboBox<>(investmentTypes);
        panel.add(investmentTypeComboBox);
        
        JButton createInvestmentButton = new JButton("Create An Investment Account");
        createInvestmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedInvestmentType = (String) investmentTypeComboBox.getSelectedItem();
                createAccount(new InvestmentBankAccount(selectedInvestmentType));
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

    private void createAccount(BankAccount account) {
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
    }

    private void viewAccounts() {
        if (accounts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No accounts to display!", "Accounts", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

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
