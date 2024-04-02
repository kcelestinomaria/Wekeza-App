package controllers.ViewBankUserController;

import java.util.List;

import models.BankUser.ViewBankUsers;

public class ViewBankUserController {
    private final ViewBankUsers model;
    private final ViewBankUserView view;

    public ViewBankUserController(ViewBankUsers model, ViewBankUserView view) {
        this.model = model;
        this.view = view;
    }

    public void showUsers() {
        List<BankUser> users = model.getUsers();
        view.updateTable(users);
    }

    public static void main(String[] args) {
        ViewBankUsers model = new ViewBankUsers();
        ViewBankUserView view = new ViewBankUserView();
        ViewBankUserController controller = new ViewBankUserController(model, view);
        controller.showUsers();
    }
}

