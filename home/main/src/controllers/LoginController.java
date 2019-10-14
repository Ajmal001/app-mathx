package main.src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.MainClass;

public class LoginController {

    @FXML
    void loginAction(ActionEvent actionEvent)
    {
        new MainClass().openHomePageWindow();
        MainClass.loginStage.close();
    }
}
