package main.src.controllers.Operator;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class NumberOperator implements ParentOperator {
    @Override
    public StackPane produceOperator(String operatorString) {
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        float shapeWidth;

        Label operator = new Label(operatorString);
        operator.setMinWidth(40);
        operator.setAlignment(Pos.CENTER);
        operator.setStyle("-fx-font-size: 14");
        operator.setTextFill(Color.WHITE);

        shapeWidth = (float) (operator.getWidth() + 50);

        Rectangle rectangle = new Rectangle(shapeWidth, 50);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setOpacity(0.8);
        rectangle.setFill(Paint.valueOf("#007AFF"));
        rectangle.setStroke(Paint.valueOf("007AFF"));
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);

        HBox hBox = new HBox();
        hBox.setMinWidth(shapeWidth);
        hBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(operator);
        stackPane.getChildren().addAll(rectangle, hBox);
        return stackPane;
    }

    @Override
    public StackPane produceLabel() {
        StackPane labelPane = new StackPane();
        labelPane.setPrefSize(200, 30);
        Label label = new Label("Numbers");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        labelPane.getChildren().addAll(label);
        return labelPane;
    }
}
