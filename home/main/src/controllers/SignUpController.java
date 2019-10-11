package main.src.controllers;
//import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import main.MainClass;
import main.src.models.TeacherSignUpModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {
    @FXML
    private TextField nameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField pswdTF;

    @FXML
    private TextField rpswdTF;

    @FXML
    private Spinner spinnerTF;

    private void showAlert(String message){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
     //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void registerAction(ActionEvent actionEvent)
    {
        String name=nameTF.getText();
        String email=emailTF.getText();
        String pswd=pswdTF.getText();
        String rpswd=rpswdTF.getText();

//Checking if Name is not empty
        if (name.isEmpty()){
            showAlert("Please enter your name");
        }

//Checking if Email is not empty
        else if (email.isEmpty()){
            showAlert("Please enter your email id ");
        }

//Checking if Email is valid
        String regex="^(.+)@(.+)$";
        Pattern p=Pattern.compile(regex);
        Matcher m =p.matcher(email);
        if(!m.matches()) {
            showAlert("Please enter a valid email id");
        }

        else if (pswd.isEmpty()){
            showAlert("Please enter your email id ");
        }
        else if (rpswd.isEmpty()){
            showAlert("please confirm password ");
        }
        else if (!pswdTF.getText().equals(rpswdTF.getText())){
            showAlert("passwords do not match ");
        }
        else
        {
            //push data to firebase

//            Firebase firebase=new Firebase("https://mathx-eea50.firebaseio.com/");
            TeacherSignUpModel model=new TeacherSignUpModel();
            model.setName(nameTF.getText());
            model.setAddress(emailTF.getText());
            model.setPassword(pswdTF.getText());
//            model.setSpeciality((int)spinnerTF.getValue());
//            firebase.child("teachers").push().setValue(model);
            //goto login
            new MainClass().openLoginWindow();
//            MainClass.signUpStage.close();

        }



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
