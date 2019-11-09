package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Mahapatra Manas, Bajaj Aditya
 * @version 1.0
 * @modified 11/3/2019
 * @since 8/30/2019
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import main.MainClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Class LoginController.
 */
public class LoginController {

    @FXML
    private Hyperlink signup;
    /**
     * The email TF.
     */
    @FXML
    private TextField emTF;
    /**
     * The pswd TF.
     */
    @FXML
    private TextField pswdTF;

    /**
     * Login action.
     * <p>
     * //  @param actionEvent the action event
     */


    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Signup Form Error");
        alert.setHeaderText(null);
        //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void signupAction(ActionEvent actionEvent) {

        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new MainClass().openSignUpWindow();
                MainClass.loginStage.close();
            }
        });
    }

    @FXML
    void loginAction(ActionEvent actionEvent) {
        String email = emTF.getText();
        String pswd = pswdTF.getText();

//Checking if Email is not empty
        if (email.isEmpty()) {
            showAlert("Please enter your email id ");
            emTF.requestFocus();
            return;
        }

//Checking if Email is valid
        String regex = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            showAlert("Please enter a valid email id");
            emTF.requestFocus();
            return;
        }

//Checking if Password is not empty
        else if (pswd.isEmpty()) {
            showAlert("Please enter a Password ");
            pswdTF.requestFocus();
            return;
        }
        new MainClass().openHomePageWindow();
        MainClass.loginStage.close();


    }
}
