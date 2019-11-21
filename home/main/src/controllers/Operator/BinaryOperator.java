package main.src.controllers.Operator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

/**
 * @author Karandeep Singh Grewal
 */

public class BinaryOperator implements ParentOperator {
    @Override
    public StackPane produceOperator(String operatorString, StackPane commonPane) {
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        float shapeWidth = 200;

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

        Label operator = new Label(operatorString);
        operator.setMinWidth(40);
        operator.setAlignment(Pos.CENTER);
        operator.setStyle("-fx-font-size: 14");
        operator.setTextFill(Color.WHITE);

        TextField input1 = new TextField();
        input1.setMinWidth(60);
        input1.setPrefWidth(60);

//        input1.textProperty().addListener((observable, oldValue, newValue) -> {
//            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
//            expressionEvaluator.produceResult((Pane) operator.getParent(), commonPane);
//        });


        final int LIMIT = 5;
        input1.lengthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (input1.getText().length() >= LIMIT) {
                        input1.setText(input1.getText().substring(0, LIMIT));
                    }
                } else if (input1.getText().length() < LIMIT)
                    rectangle.setWidth(190);
            }
        });

        TextField input2 = new TextField();
        input2.setMinWidth(60);
        input2.setPrefWidth(60);

//        input2.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (oldValue != newValue) {
//                ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
//                expressionEvaluator.produceResult((Pane) operator.getParent(), commonPane);
//            }
//        });


        input2.lengthProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (input2.getText().length() >= LIMIT) {
                        input2.setText(input2.getText().substring(0, LIMIT));
                    }
                } else if (input2.getText().length() < LIMIT)
                    rectangle.setWidth(190);
            }
        });

        hBox.getChildren().addAll(input1, operator, input2);
        stackPane.getChildren().addAll(rectangle, hBox);
        return stackPane;
    }

    @Override
    public StackPane produceLabel() {
        StackPane labelPane = new StackPane();
        labelPane.setPrefSize(200, 30);
        Label label = new Label("Operators");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        labelPane.getChildren().addAll(label);
        return labelPane;
    }
}
