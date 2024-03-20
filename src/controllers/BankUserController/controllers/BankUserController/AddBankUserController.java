package controllers.BankUserController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.BankUser.AddBankUser;

public class AddBankUserController {
    
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private final AddBankUser addBankUser = new AddBankUser();

    @FXML
    private void addUser(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        addBankUser.setBankUserDetails(username, password);
    }
}
