import javax.swing.*;

import models.BankUser.AddBankUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBankUserView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton addUserButton;

    public AddBankUserView() {
        setTitle("Add Bank User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the window

        initComponents();
        addComponentsToFrame();
        setVisible(true);
    }

    private void initComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new AddUserListener());
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(addUserButton);

        add(panel);
    }

    private class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            AddBankUser addBankUser = new AddBankUser();
            addBankUser.setBankUserDetails(username, password);
        }
    }
}
