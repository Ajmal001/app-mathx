package main.src.controllers.Listeners;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ResultOrQuestionListeners {
    public void switchPane(StackPane stackPane) {

        StackPane resultPane = new StackPane();
        resultPane.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));

        Pane questionPane = new Pane();
        questionPane.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        //Switches StackPane(resultPane) to Pane(questionPane)
        if (stackPane.getChildren().toString().contains("StackPane")) {

            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(questionPane);
        } else {
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(resultPane);

        }
    }
}
