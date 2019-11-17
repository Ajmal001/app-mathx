package main.src.controllers.Listeners;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class CommonPaneListener {
    StackPane questionPane = new StackPane();
    Pane resultPane = new Pane();


    public void produceQuestionPane(StackPane questionPane, String question) {
        questionPane.setAlignment(Pos.TOP_LEFT);
        Label tempLabel = new Label("Questions");
        tempLabel.setStyle("-fx-font-size: 24;");
        Label questionLabel = new Label(question);
        questionLabel.setStyle("-fx-font-size: 20");
        questionPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#F5F5F5"), CornerRadii.EMPTY, Insets.EMPTY)));
        VBox vBox = new VBox(tempLabel, questionLabel);
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setMaxHeight(520);
        scrollPane.setMaxWidth(1670);
        questionPane.getChildren().add(scrollPane);
    }

    private void produceResultPane(Pane resultPane) {
        Label tempLabel = new Label("Result");
        tempLabel.setStyle("-fx-font-size: 24;");
        resultPane.getChildren().add(tempLabel);
        resultPane.setBackground(new Background(new BackgroundFill(Color.valueOf("E5E5E5"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void produceCommonPane(StackPane stackPane, String question) {
        produceQuestionPane(questionPane, question);
        produceResultPane(resultPane);
        stackPane.getChildren().add(questionPane);
    }

    public void switchPane(StackPane stackPane) {
        //Switches StackPane(resultPane) to Pane(questionPane)
        if (stackPane.getChildren().toString().contains("StackPane")) {
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(resultPane);
        } else {
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(questionPane);
        }
    }
}
