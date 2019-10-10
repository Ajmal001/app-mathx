package main.src.controllers;
//import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import main.MainClass;
import main.src.models.TeacherSignUpModel;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField nameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField confirmPasswordTF;

    @FXML
    private Spinner spinnerTF;



    public void showAlert(String message){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void registerAction(ActionEvent actionEvent)
    {

        if (nameTF.getText().isEmpty()){
            showAlert("please enter your name ");

        }
        else if (emailTF.getText().isEmpty()){
            showAlert("please enter your email ");

        }
        else if (passwordTF.getText().isEmpty()){
            showAlert("please enter your password ");

        }

        else if (passwordTF.getText().length()<=7){
            showAlert("Password must be larger than 7 letters ");
        }

        else if (confirmPasswordTF.getText().isEmpty()==true){
            showAlert("please confirm password ");
        }

        else if (!passwordTF.getText().equals(confirmPasswordTF.getText())){
            showAlert("passwords do not match ");
        }

        else
        {
            //push data to firebase

//            Firebase firebase=new Firebase("https://mathx-eea50.firebaseio.com/");
            TeacherSignUpModel model=new TeacherSignUpModel();
            model.setName(nameTF.getText());
            model.setAddress(emailTF.getText());
            model.setPassword(passwordTF.getText());
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
