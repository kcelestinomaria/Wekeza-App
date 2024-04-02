package controllers;

import javax.swing.*;

import models.Login;
import views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private Login login; // Assuming you have a Login class representing the model

    public LoginController(LoginView loginView, Login login) {
        this.loginView = loginView;
        this.login = login;
        this.loginView.addLoginListener(new LoginListener());
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            // Perform authentication logic using the model
            boolean isAuthenticated = login.authenticate(username, password);
            if (isAuthenticated) {
                // Redirect to main application view
                JOptionPane.showMessageDialog(loginView, "Login successful!");
                // Replace JOptionPane with your actual main application view
            } else {
                JOptionPane.showMessageDialog(loginView, "Invalid username or password. Please try again.");
            }
        }
    }
}
