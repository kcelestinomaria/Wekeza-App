package controllers.BankUserController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.BankUser.UpdateBankUser;

public class UpdateBankUserController {
    
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    private final UpdateBankUser updateBankUser = new UpdateBankUser();

    @FXML
    private void updateUser(){
        String username = usernameField.getText();
        String newPassword = passwordField.getText();
        updateBankUser.setBankUserDetails(username, newPassword);
    }
}
