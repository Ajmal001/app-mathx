package main.src.controllers;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


public interface Expression {
    void produceExpressionType(VBox vBox);
    StackPane produceShape(String s);
    Pane produceLabelPane();
}
