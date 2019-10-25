package main.src.controllers;
import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
    private ComboBox grade;

    private void showAlert(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Signup Form Error");
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
    //    String g=(String) grade.getValue();

//Checking if Name is not empty
        if (name.isEmpty()){
            showAlert("Please enter your name");
            nameTF.requestFocus();
            return;
        }

//Checking if Email is not empty
        else if (email.isEmpty()){
            showAlert("Please enter your email id ");
            emailTF.requestFocus();
            return;
        }

//Checking if Email is valid
        String regex="^(.+)@(.+)$";
        Pattern p=Pattern.compile(regex);
        Matcher m =p.matcher(email);
        if(!m.matches()) {
            showAlert("Please enter a valid email id");
            emailTF.requestFocus();
            return;
        }

//Checking if Password is not empty
        else if (pswd.isEmpty()){
            showAlert("Please enter a Password ");
            pswdTF.requestFocus();
            return;
        }

//Checking if Password is valid
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        String error="";

        if (pswd.length() < 8) {
            error+="-> Password must have atleast 8 characters\n";
        }
        if (pswd.length() > 20) {
            error+="-> Password must have atmost 20 characters\n";
        }
        if (!specialCharPatten.matcher(pswd).find()) {
            error+="-> Password must have atleast one special character\n";
        }
        if (!UpperCasePatten.matcher(pswd).find()) {
            error+="-> Password must have atleast one uppercase character\n";
        }
        if (!lowerCasePatten.matcher(pswd).find()) {
            error+="-> Password must have atleast one lowercase character\n";
        }
        if (!digitCasePatten.matcher(pswd).find()) {
            error+="-> Password must have atleast one digit character\n";
        }
        if(error.length()>0) {
            showAlert("Please re-enter your password\n"+error);
            pswdTF.requestFocus();
        }


//Checking if both Passwords are same
        else if (!pswd.equals(rpswd)) {
            showAlert("Passwords do not match ");
            rpswdTF.requestFocus();
            return;
        }


//Checking if Grade is selected
        else if(grade.getValue()==null){
            showAlert("Please choose a Grade");
            return;
        }

        else
        {
            //push data to firebase

            Firebase firebase=new Firebase("https://mathx-eea50.firebaseio.com/");
            TeacherSignUpModel model=new TeacherSignUpModel();
            model.setName(nameTF.getText());
            model.setAddress(emailTF.getText());
            model.setPassword(pswdTF.getText());
            //model.setSpeciality((int)spinnerTF.getValue());
            firebase.child("teachers").push().setValue(model);
            //goto login
            new MainClass().openLoginWindow();
//            MainClass.signUpStage.close();

        }



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
