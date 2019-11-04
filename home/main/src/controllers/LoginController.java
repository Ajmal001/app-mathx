package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author 		 Mahapatra Manas, Bajaj Aditya
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import main.MainClass;


/**
 * The Class LoginController.
 */
public class LoginController {

    @FXML
    private Hyperlink signup;

    /**
     * Login action.
     *
     * @param actionEvent the action event
     */
    @FXML
    void loginAction(ActionEvent actionEvent)
    {
        new MainClass().openHomePageWindow();
        MainClass.loginStage.close();

    }
}
