package main;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Mahapatra Manas, Bajaj Aditya, Mehta Ria, Sheran Dass, Karandeep Singh Grewal
 */

public class MainClass extends Application {

    public static Stage signUpStage, loginStage, homePageStage, workspaceStage, asgnStage, create_asgnStage, view_asgnStage, practStage, adminStage;

    public static void main(String[] args) {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        InputStream serviceAccount = new FileInputStream(new File(getClass().getResource("src/controllers/jsonFile.json").getFile()));
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setDatabaseUrl("https://ser515-team4.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        asgnStage = primaryStage;
        openLoginWindow();

    }


    public void listWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/ListViewUi.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            practStage = new Stage();
            practStage.setScene(scene);
            practStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openSignUpWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/signup.fxml"));
            GridPane pane = loader.load();
            Scene scene = new Scene(pane);
            signUpStage = new Stage();
            signUpStage.setScene(scene);
            signUpStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openLoginWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/loginpage.fxml"));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void openAdminWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/adminpage.fxml"));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            adminStage = new Stage();
            adminStage.setScene(scene);
            adminStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openHomePageWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/homepage.fxml"));
            GridPane pane = loader.load();
            Scene scene = new Scene(pane);
            homePageStage = new Stage();
            homePageStage.setScene(scene);
            homePageStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openWorkSpaceWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/workspace.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane, 1920, 1080);
            workspaceStage = new Stage();
            workspaceStage.setTitle("Workspace");
            workspaceStage.setScene(scene);
            workspaceStage.resizableProperty().setValue(Boolean.FALSE);
            workspaceStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void assignmentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/assignment.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            asgnStage = new Stage();
            asgnStage.setTitle("Assignments");
            asgnStage.setScene(scene);
            asgnStage.resizableProperty().setValue(Boolean.FALSE);
            asgnStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void view_assignmentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/view_assignment.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            view_asgnStage = new Stage();
            view_asgnStage.setTitle("View Assignments");
            view_asgnStage.setScene(scene);
            view_asgnStage.resizableProperty().setValue(Boolean.FALSE);
            view_asgnStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create_assignmentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("res/view/create_assignment.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            create_asgnStage = new Stage();
            create_asgnStage.setTitle("Create Assignment");
            create_asgnStage.setScene(scene);
            create_asgnStage.resizableProperty().setValue(Boolean.FALSE);
            create_asgnStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
