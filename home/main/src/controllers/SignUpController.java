package main.src.controllers;

/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University
 * @author 		 Bajaj Aditya, Mahapatra Manas
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */

import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.MainClass;
import main.src.models.StudentSignUpModel;
import main.src.models.TeacherSignUpModel;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Class SignUpController.
 */
public class SignUpController implements Initializable {
    
    /** The name TF. */
    @FXML
    private TextField nameTF;

    /** The email TF. */
    @FXML
    private TextField emailTF;

    /** The pswd TF. */
    @FXML
    private TextField pswdTF;

    /** The rpswd TF. */
    @FXML
    private TextField rpswdTF;

    /** The grade. */
    @FXML
    private ComboBox grade;

    /** The User. */
    @FXML
    private ToggleGroup User;

    /**
     * Show alert.
     *
     * @param message the message
     */
    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Signup Form Error");
        alert.setHeaderText(null);
        //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Register action.
     *
     * @param actionEvent the action event
     */
    @FXML
    void registerAction(ActionEvent actionEvent) {
        String name = nameTF.getText();
        String email = emailTF.getText();
        String pswd = pswdTF.getText();
        String rpswd = rpswdTF.getText();
        //    String g=(String) grade.getValue();

//Checking if Name is not empty
        if (name.isEmpty()) {
            showAlert("Please enter your name");
            nameTF.requestFocus();
            return;
        }

//Checking if Email is not empty
        else if (email.isEmpty()) {
            showAlert("Please enter your email id ");
            emailTF.requestFocus();
            return;
        }

//Checking if Email is valid
        String regex = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            showAlert("Please enter a valid email id");
            emailTF.requestFocus();
            return;
        }

//Checking if Password is not empty
        else if (pswd.isEmpty()) {
            showAlert("Please enter a Password ");
            pswdTF.requestFocus();
            return;
        }

//Checking if Password is valid
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        String error = "";

        if (pswd.length() < 8) {
            error += "-> Password must have atleast 8 characters\n";
        }
        if (pswd.length() > 20) {
            error += "-> Password must have atmost 20 characters\n";
        }
        if (!specialCharPatten.matcher(pswd).find()) {
            error += "-> Password must have atleast one special character\n";
        }
        if (!UpperCasePatten.matcher(pswd).find()) {
            error += "-> Password must have atleast one uppercase character\n";
        }
        if (!lowerCasePatten.matcher(pswd).find()) {
            error += "-> Password must have atleast one lowercase character\n";
        }
        if (!digitCasePatten.matcher(pswd).find()) {
            error += "-> Password must have atleast one digit character\n";
        }
        if (error.length() > 0) {
            showAlert("Please re-enter your password\n" + error);
            pswdTF.requestFocus();
        }


//Checking if both Passwords are same
        else if (!pswd.equals(rpswd)) {
            showAlert("Passwords do not match ");
            rpswdTF.requestFocus();
            return;
        }


//Checking if Grade is selected
        else if (grade.getValue() == null) {
            showAlert("Please choose a Grade");
            return;
        } else {
            //Push data into firebase

            RadioButton selectedRadiobutton = (RadioButton) User.getSelectedToggle();

            //Firebase firebase = new Firebase("https://mathx-eea50.firebaseio.com/");
              Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");


            //Create a Teacher or Student depending on the type selected

            if (selectedRadiobutton.getText().equals("Teacher")) {

                TeacherSignUpModel model = new TeacherSignUpModel();

                model.setName(nameTF.getText());
                model.setAddress(emailTF.getText());
                model.setPassword(pswdTF.getText());
                model.setGrade(grade.getValue().toString());

                firebase.child(selectedRadiobutton.getText()).push().setValue(model);

            } else {

                StudentSignUpModel model = new StudentSignUpModel();

                model.setName(nameTF.getText());
                model.setAddress(emailTF.getText());
                model.setPassword(pswdTF.getText());
                model.setGrade(grade.getValue().toString());

                firebase.child(selectedRadiobutton.getText()).push().setValue(model);


            }
            //goto login
            new MainClass().openLoginWindow();
            MainClass.signUpStage.close();

        }


    }

    /**
     * Initialize.
     *
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
