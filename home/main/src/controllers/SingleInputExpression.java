package main.src.controllers;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SingleInputExpression implements Expression{

    @Override
    public void produceExpression(VBox vBox) {
        Button button = new Button("HEy Single");
        vBox.getChildren().addAll(button);

    }
}
