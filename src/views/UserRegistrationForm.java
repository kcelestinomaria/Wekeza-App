package views;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import models.UserRegistrationModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationForm extends JFrame {
    private static JTextField usernameField;
    private static JTextField emailField;
    private static JPasswordField passwordField;
    private UserRegistrationModel model;

    public UserRegistrationForm(UserRegistrationModel model) {
        this.model = model;

        setTitle("User Registration");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FFFFFF")); // Set background color


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    /**
     * 
     */
    private static void registerUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Check if the email is already registered
        if (UserRegistrationModel.isEmailRegistered(email)) {
            JOptionPane.showMessageDialog(null, "Email already registered", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Inside the action listener for the "Register" button
        String errorMessage = UserRegistrationModel.registerUser(username, email, password);
        if (errorMessage.startsWith("Error")) {
            JTextComponent errorMessageLabel = new JTextField(); //views.UserRegistrationForm.registerUser();
            errorMessageLabel.setText(errorMessage);
            //errorMessageLabel.setText(errorMessage); // Display error message in a label
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Success", JOptionPane.INFORMATION_MESSAGE); // Or display                                                                                            //                                                                       // using                                                                                               // JOptionPane
        }

        UserRegistrationModel.registerUser(username, email, password);
    }

    public static void main(String[] args) {
        UserRegistrationModel model = new UserRegistrationModel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserRegistrationForm(model);
            }
        });
    }
}
