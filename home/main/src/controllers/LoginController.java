package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Mahapatra Manas, Bajaj Aditya, Sheran Dass
 * @version 1.0
 * @modified 11/3/2019
 * @since 8/30/2019
 */

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.MainClass;
import main.src.models.AdminModel;
import main.src.models.StudentSignUpModel;
import main.src.models.TeacherSignUpModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The Class LoginController.
 */
public class LoginController implements Initializable {

    @FXML
    private Button login;


    @FXML
    private Hyperlink signup;
    List<TeacherSignUpModel> teachersList = new ArrayList<>();
    List<StudentSignUpModel> studentsList = new ArrayList<>();
    List<AdminModel> adminList = new ArrayList<>();
    public static TeacherSignUpModel teacherModel = new TeacherSignUpModel();
    public static StudentSignUpModel studentModel = new StudentSignUpModel();
    public static AdminModel adminModel = new AdminModel();


    public LoginController() {
//        System.out.println("In Constructor");
        //Firebase firebase=new Firebase("https://mathx-eea50.firebaseio.com/");
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");

        firebase.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    adminModel = data.getValue(AdminModel.class);
                    adminModel.setId(data.getKey());
                    adminList.add(adminModel);

                }


            }
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        firebase.child("Teacher").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    teacherModel = data.getValue(TeacherSignUpModel.class);
                    teacherModel.setId(data.getKey());
                    teachersList.add(teacherModel);

                }


            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        firebase.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    studentModel = data.getValue(StudentSignUpModel.class);
                    studentModel.setId(data.getKey());
                    studentsList.add(studentModel);

                }

            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

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
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void signupAction(ActionEvent actionEvent) {


        new MainClass().openSignUpWindow();
//        MainClass.loginStage.close();

    }

    int returnValue = 0;

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
        } else {
            String type = "";
            for(int i = 0; i < adminList.size(); i++){
                AdminModel model = adminList.get(i);
                if (emTF.getText().equals(model.getAddress()) && pswdTF.getText().equals(model.getPassword())) {
                    returnValue = 1;
                    adminModel = adminList.get(i);
                    type = "Admin";
                    studentModel = null;
                    teacherModel = null;
                }
            }
//            System.out.println(teachersList.size());
            for (int i = 0; i < teachersList.size(); i++) {
                TeacherSignUpModel model = teachersList.get(i);
                if (emTF.getText().equals(model.getAddress()) && pswdTF.getText().equals(model.getPassword())) {
                    returnValue = 1;
                    teacherModel = teachersList.get(i);
                    type = "Teacher";
                    studentModel = null;
                    adminModel = null;
                }
            }
//            System.out.println(studentsList.size());
            for (int i = 0; i < studentsList.size(); i++) {
                StudentSignUpModel model = studentsList.get(i);
                if (emTF.getText().equals(model.getAddress()) && pswdTF.getText().equals(model.getPassword())) {
                    returnValue = 1;
                    studentModel = studentsList.get(i);
                    type = "Student";
                    teacherModel = null;
                    adminModel = null;
                }
            }
            if (returnValue == 1 && type.equals("Admin")) {
//                showSuccess("Admin" + adminModel.getName() + "Logged in Successfully" );
                new MainClass().openAdminWindow();
                MainClass.loginStage.close();
            } else if (returnValue == 1 && type.equals("Teacher")) {
//                showSuccess("Teacher" + teacherModel.getName() + "Logged in Successfully");
                //uncomment when done
                new MainClass().assignmentWindow();
                MainClass.loginStage.close();

            } else if (returnValue == 1 && type.equals("Student")) {
//                showSuccess("Student" + studentModel.getName() + "Logged in Successfully");
                //uncomment when done
                new MainClass().openHomePageWindow();
                MainClass.loginStage.close();
            } else if (returnValue == 0) {
                showAlert("Invalid Login Credentials ");

            }


        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public static void main(String[] args) {

    }

    public void adminClick(ActionEvent actionEvent) {
        WebView myWebView = new WebView();
        WebEngine engine = myWebView.getEngine();


        engine.load("https://ser515-team4.firebaseio.com/");
        VBox vBox = new VBox(myWebView);
        Scene scene = new Scene(vBox, 1200, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
