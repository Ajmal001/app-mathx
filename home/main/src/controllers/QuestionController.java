package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Bajaj Aditya
 * @version 1.0
 * @modified 11/3/2019
 * @since 8/30/2019
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The Class question.
 */
public class QuestionController implements Initializable {

    /**
     * The question TF.
     */
    @FXML
    private TextField quesTF;

    /**
     * The answer TF.
     */
    @FXML
    private TextField ansTF;

    /**
     * The grade.
     */
    @FXML
    private ComboBox grade;

    /**
     * The difficulty.
     */
    @FXML
    private ComboBox diff;


    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Add Questions Form Error");
        alert.setHeaderText(null);
        //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert2(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question Added");
        //   alert.setHeaderText("Required Fields Empty");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ButtonType b=new ButtonType("Add Another Question");
        alert.getButtonTypes().addAll(b);
        Optional<ButtonType> op=alert.showAndWait();

        if(op.get()==b){
            //pop new window with clear text fields
        }
    }

    @FXML
    void addquestion(ActionEvent actionEvent) {
        String ques = quesTF.getText();
        String ans = ansTF.getText();
//Checking if question is not empty
        if (ques.isEmpty()) {
            showAlert("Please enter your name");
            quesTF.requestFocus();
            return;
        }

//Checking if Answer is not empty
        else if (ans.isEmpty()) {
            showAlert("Please enter your email id ");
            ansTF.requestFocus();
            return;
        }

//Checking if Grade is selected
        else if (grade.getValue() == null) {
            showAlert("Please choose a Grade");
            return;
        }

//Checking if Difficulty  is selected
        else if (diff.getValue() == null) {
            showAlert("Please choose Difficulty of question");
            return;
        }
    }

    /*
                Firebase firebase = new Firebase("https://mathx-eea50.firebaseio.com/");
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
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
