import javax.swing.*;

import models.BankUser.UpdateBankUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBankUserView extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton updateUserButton;

    public UpdateBankUserView() {
        setTitle("Update Bank User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the window

        initComponents();
        addComponentsToFrame();
        setVisible(true);
    }

    private void initComponents() {
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        updateUserButton = new JButton("Update User");
        updateUserButton.addActionListener(new UpdateUserListener());
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("New Password:"));
        panel.add(passwordField);
        panel.add(updateUserButton);

        add(panel);
    }

    private class UpdateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String newPassword = passwordField.getText();
            UpdateBankUser updateBankUser = new UpdateBankUser();
            updateBankUser.setBankUserDetails(username, newPassword);
        }
    }
}
