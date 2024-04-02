package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {

    public HomeView() {
        setTitle("Wekeza App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Choose what to do:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login button click event
                // Open login page
                LoginView loginView = new LoginView();
                dispose(); // Close the current window
            }
        });

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sign-up button click event
                // Open sign-up page
               SignUpView signUpView = new SignUpView();
                dispose(); // Close the current window
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private void addComponentsToFrame() {
        // No need to add components explicitly as they are added to the content pane in initComponents
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomeView();
            }
        });
    }
}
