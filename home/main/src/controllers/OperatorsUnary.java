package main.src.controllers;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

/*
@author - Karandeep Singh Grewal
*/

public class OperatorsUnary implements Operator {

    private float shapeWidth = 110;
    private float shapeHeight = 50;

    @Override
    public void produceExpressionType(VBox vBox) {
        vBox.getChildren().addAll(produceLabelPane(), produceShape("log"),
                produceShape("lg"), produceShape("sin"),produceShape("cos"),produceShape("tan"));

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
        rectangle.setFill(Paint.valueOf("#FF2D55"));
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);

        String operationFullName = null;
        if(string.equals("log"))
            operationFullName = "Logarithm - Base e";
        if(string.equals("sin"))
            operationFullName = "Sin(x)";
        if(string.equals("cos"))
            operationFullName = "Cos(x)";
        if(string.equals("tan"))
            operationFullName = "Tan(x)";
        if(string.equals("lg"))
            operationFullName = "Logarithm - Base 2";

        HBox hBox = new HBox();
        hBox.setMinWidth(shapeWidth);
        hBox.setAlignment(Pos.CENTER);

        Tooltip t =new Tooltip(operationFullName);
        t.setShowDelay(new Duration(0));
        if(operationFullName !=null)
        Tooltip.install(hBox,t);

        TextField input = new TextField();
        input.setMinWidth(40);
        input.setPrefWidth(40);

        Label operator = new Label(string);
        operator.setMinWidth(40);
        operator.setPrefWidth(40);
        operator.setStyle("-fx-font-size: 14");
        operator.setTextFill(Color.WHITE);

        hBox.getChildren().addAll(operator, input);
        operatorPane.getChildren().addAll(rectangle, hBox);
        return operatorPane;
    }

    @Override
    public Pane produceLabelPane() {
        Pane labelPane = new Pane();
        labelPane.setPrefSize(200, 50);
        Label label = new Label("Unary Operators");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        labelPane.getChildren().addAll(label);
        return labelPane;
    }


}
