package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;
    private JLabel titleLabel;

    public HomePage() {
        setTitle("WEKEZA Home Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        getContentPane().setBackground(Color.decode("#54e6b6")); // Set background color


        initComponents();
    }

    private void initComponents() {
        titleLabel = new JLabel("Welcome to WEKEZA Neobank App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE); // Set text color


        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Open the login page
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                //dispose(); // Close the current window
                dispose();
                //new AccountsView();
            }
        
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Open the registration page
                UserRegistrationForm userRegistrationForm = new UserRegistrationForm(null);
                userRegistrationForm.setVisible(true);
                dispose(); // Close the current window
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#54e6b6")); // Set background color
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomePage homePage = new HomePage();
                homePage.setVisible(true);
                new AccountsView();
            }
        });
    }
}
