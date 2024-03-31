package controllers.BankUserController;

import java.awt.GridLayout;

import javax.swing.*;

import models.BankUser.AddBankUser;

public class AddBankUserController {
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public AddBankUserController(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    public void addUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        AddBankUser addBankUser = new AddBankUser();
        addBankUser.setBankUserDetails(username, password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField usernameField = new JTextField(20);
            JPasswordField passwordField = new JPasswordField(20);
            JButton addUserButton = new JButton("Add User");
            addUserButton.addActionListener(e -> {
                AddBankUserController controller = new AddBankUserController(usernameField, passwordField);
                controller.addUser();
            });

            JPanel panel = new JPanel(new GridLayout(3, 1));
            panel.add(new JLabel("Username:"));
            panel.add(usernameField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);
            panel.add(addUserButton);

            JFrame frame = new JFrame("Add Bank User");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
