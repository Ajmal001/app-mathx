package main.src.controllers;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class OperatorsBinary implements Expression{

    @Override
    public void produceExpressionType(VBox vBox) {
        Pane labelPane = new Pane();

        labelPane.setPrefSize(200,100);
        Label label=new Label("Double Input Expressions");
        labelPane.getChildren().addAll(label);

        Rectangle rectangle=new Rectangle(170,40);

        vBox.getChildren().addAll(labelPane,rectangle);

    }

    @Override
    public StackPane produceShape(String s) {
        return null;
    }


    @Override
    public Pane produceLabelPane() {
        return null;
    }
}
