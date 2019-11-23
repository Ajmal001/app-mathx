package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author       Mehta Ria
 * @version 	 1.1
 * @since        8/30/2019
 * @modified     11/3/2019
 */

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.MainClass;


/**
 * The Class AlertBox.
 */
public class AlertBox {

    /**
     * Display.
     */
    @FXML
    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Logout");
        window.setMinWidth(250);

        //Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 3, 3, 3));
        grid.setVgap(5);
        grid.setHgap(0);

        //Message
        Label label=new Label();
        label.setText("Are you sure you want to logout?");
        GridPane.setConstraints(label, 0,0);

        //Option Buttons

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        noButton.setOnAction(e -> window.close());
        yesButton.setOnAction(e -> {
            window.close();
            MainClass.homePageStage.close();
            new MainClass().openLoginWindow();

        });

        GridPane.setConstraints(yesButton,1,3);
        GridPane.setConstraints(noButton, 0,3);

//        VBox layout= new VBox(10);
        grid.getChildren().addAll(label, noButton, yesButton);
//        grid.setAlignment(Pos.CENTER_LEFT);
//        StackPane.setAlignment(yesButton,Pos.BOTTOM_LEFT);
//        StackPane.setAlignment(noButton, Pos.BASELINE_RIGHT);

        Scene scene = new Scene(grid, 270, 100);
        window.setScene(scene);
        window.showAndWait();
    }
}
