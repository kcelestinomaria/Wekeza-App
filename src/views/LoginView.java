package views;

import javax.swing.*;

import models.Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private static final int SESSION_TIMEOUT = 10 * 60 * 1000; // 10 minutes in milliseconds
    private Timer sessionTimer;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.decode("#FFFFFF")); // Set background color


        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 10, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 10, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 40, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                startSessionTimer();

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Call the login method from your model
                String errorMessage = Login.authenticate(username, password); // log in from here
                if (errorMessage.startsWith("Error")) {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Redirect to main application window or perform other actions
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    // Log out user if they stay inactive for 10 minutes
    private void startSessionTimer() {
        if (sessionTimer != null && sessionTimer.isRunning()) {
            sessionTimer.stop();
        }
        sessionTimer = new Timer(SESSION_TIMEOUT, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform logout logic here
                // For example, redirect to the login page or display a logout message
                JOptionPane.showMessageDialog(LoginView.this, "Session timeout. Logging out...");
                // Stop the timer
                sessionTimer.stop();
            }
        });
        sessionTimer.setRepeats(false); // Only run once
        sessionTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView();
            }
        });
    }
}
