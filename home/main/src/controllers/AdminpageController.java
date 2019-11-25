package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Sheran Dass
 * @version 1.0
 * @modified 11/24/2019
 * @since 8/24/2019
 */
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import main.MainClass;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminpageController implements Initializable {

    public void adminClick(ActionEvent actionEvent) throws Exception {

        Stage stage = new Stage();
        Stage adminStage = stage;
        fire(adminStage);

        return;
    }

    public void fire(Stage primaryStage) throws Exception {
        WebView myWebView = new WebView();
        WebEngine engine = myWebView.getEngine();


        engine.load("https://ser515-team4.firebaseio.com/");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(myWebView);
        Scene scene = new Scene(vBox, 1380, 768);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void adminLogout(ActionEvent actionEvent) {
        new MainClass().openLoginWindow();
        MainClass.adminStage.close();
    }
}
