package main.src.controllers;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//Interface Class - Operator
public interface Operator {
    void produceExpressionType(VBox vBox);
    StackPane produceShape(String s);
    Pane produceLabelPane();
}
