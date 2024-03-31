package controllers.BankUserController;
import java.awt.GridLayout;

import javax.swing.*;

import models.BankUser.UpdateBankUser;

public class UpdateBankUserController {
    private final JTextField usernameField;
    private final JTextField passwordField;

    public UpdateBankUserController(JTextField usernameField, JTextField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    public void updateUser() {
        String username = usernameField.getText();
        String newPassword = passwordField.getText();
        UpdateBankUser updateBankUser = new UpdateBankUser();
        updateBankUser.setBankUserDetails(username, newPassword);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextField usernameField = new JTextField(20);
            JTextField passwordField = new JTextField(20);
            JButton updateUserButton = new JButton("Update User");
            updateUserButton.addActionListener(e -> {
                UpdateBankUserController controller = new UpdateBankUserController(usernameField, passwordField);
                controller.updateUser();
            });

            JPanel panel = new JPanel(new GridLayout(3, 1));
            panel.add(new JLabel("Username:"));
            panel.add(usernameField);
            panel.add(new JLabel("New Password:"));
            panel.add(passwordField);
            panel.add(updateUserButton);

            JFrame frame = new JFrame("Update Bank User");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.add(panel);
            frame.setVisible(true);
        });
    }
}

