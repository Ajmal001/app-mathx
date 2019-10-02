package main.src.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class signupController implements Initializable {
    @FXML
    private TextField nameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField useridTF;

    @FXML
    private TextField passwordTF;

    @FXML
    void registerAction(ActionEvent actionEvent)
    {
        System.out.println("called signup");
//        Firebase firebase=new Firebase("https://mathx-eea50.firebaseio.com/");
//        //firebase.child("Manas").setValue("Mahapatra");
//        TeacherSignupModel model = new TeacherSignupModel();
//        model.setName(nameTF.getText());
//        model.setPassword(passwordTF.getText());
//  //      model.setUsername(usernameTF.getText());
//        model.setPhoto("/sample/src/4.jpg");
// //       model.setSummary("hello , iam using your mainClass");
//  //      model.setAddress(addressTF.getText());
// //       model.setSpeciality(specTF.getText());
//  //      model.setPhoneNumber(phoneNumberTF.getText());
//
//        firebase.child("teachers").push().setValue(model);


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
