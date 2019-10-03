package main.src.controllers;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 3, 3, 3));
        grid.setVgap(5);
        grid.setHgap(0);

        //Message
        Label label=new Label();
        label.setText(message);
        GridPane.setConstraints(label, 0,0);

        //Option Buttons
        Button noButton = new Button("No");
        Button yesButton = new Button("Yes");
        noButton.setOnAction(e -> window.close());
        yesButton.setOnAction(e -> window.close());

        GridPane.setConstraints(yesButton,1,3);
        GridPane.setConstraints(noButton, 0,3);

//        VBox layout= new VBox(10);
        grid.getChildren().addAll(label, noButton, yesButton);
//        grid.setAlignment(Pos.CENTER_LEFT);
//        StackPane.setAlignment(yesButton,Pos.BOTTOM_LEFT);
//        StackPane.setAlignment(noButton, Pos.BASELINE_RIGHT);

        Scene scene=new Scene(grid,250,80);
        window.setScene(scene);
        window.showAndWait();
    }
}