package main.src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.MainClass;

public class HomepageController {


    @FXML
    void workspaceAction(ActionEvent actionEvent)
    {
        new MainClass().openWorkSpaceWindow();
        MainClass.homePageStage.close();
    }
}
