package main.src.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class OperatorsBinary implements Operator {

    private float shapeWidth = 165;
    private float shapeHeight = 50;

    @Override
    public void produceExpressionType(VBox vBox) {
        vBox.getChildren().addAll(produceLabelPane(), produceShape("+"), produceShape("-"),
                produceShape("*"), produceShape("/"));
    }

    @Override
    public StackPane produceShape(String string) {
        StackPane operatorPane = new StackPane();
        operatorPane.setPrefSize(200, 50);
        operatorPane.setAlignment(Pos.CENTER);

        Rectangle rectangle = new Rectangle(shapeWidth, 50);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setOpacity(0.8);
        rectangle.setFill(Paint.valueOf("#007AFF"));
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);

        String operationFullName = null;
        if(string.equals("+"))
            operationFullName = "Addition";
        if(string.equals("/"))
            operationFullName = "Division";
        if(string.equals("-"))
            operationFullName = "Subtraction";
        if(string.equals("*"))
            operationFullName = "Multiplication";

        HBox hBox = new HBox();
        hBox.setMinWidth(shapeWidth);
        hBox.setAlignment(Pos.CENTER);

        Tooltip t =new Tooltip(operationFullName);
        t.setShowDelay(new Duration(0));
        if(operationFullName !=null)
        Tooltip.install(hBox,t);

        TextField input1 = new TextField();
        input1.setMinWidth(40);
        input1.setPrefWidth(40);

        Label operator = new Label(string);
        operator.setMinWidth(40);
        operator.setPrefWidth(40);
        operator.setAlignment(Pos.CENTER);
        operator.setStyle("-fx-font-size: 14");
        operator.setTextFill(Color.WHITE);


        TextField input2 = new TextField();
        input2.setMinWidth(40);
        input2.setPrefWidth(40);

        hBox.getChildren().addAll(input1, operator, input2);
        operatorPane.getChildren().addAll(rectangle, hBox);


        return operatorPane;
    }


    @Override
    public Pane produceLabelPane() {
        Pane labelPane = new Pane();
        labelPane.setPrefSize(200, 50);
        Label label = new Label("Binary Operators");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        labelPane.getChildren().addAll(label);
        return labelPane;
    }

}
