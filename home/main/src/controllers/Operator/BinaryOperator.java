package main.src.controllers.Operator;

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
    //produces the operator of binary type
    public StackPane produceOperator(String operatorString, StackPane commonPane) {

        final int LIMIT = 5;    //Limit of numbers in the input - 5 for lower grades
        float shapeWidth = 220;

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        Rectangle rectangle = new Rectangle(shapeWidth, 50);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setFill(Paint.valueOf("#FF3B30"));
        rectangle.setStroke(Paint.valueOf("FF3B30"));
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
        input1.setMinWidth(70);
        input1.setPrefWidth(70);
        input1.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: BOLD");
        input1.lengthProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (input1.getText().length() >= LIMIT) {
                    input1.setText(input1.getText().substring(0, LIMIT));
                }
            } else if (input1.getText().length() < LIMIT)
                rectangle.setWidth(190);
        });

        TextField input2 = new TextField();
        input2.setMinWidth(70);
        input2.setPrefWidth(70);
        input2.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: BOLD");
        input2.lengthProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (input2.getText().length() >= LIMIT) {
                    input2.setText(input2.getText().substring(0, LIMIT));
                }
            } else if (input2.getText().length() < LIMIT)
                rectangle.setWidth(190);
        });

        hBox.getChildren().addAll(input1, operator, input2);
        stackPane.getChildren().addAll(rectangle, hBox);
        return stackPane;
    }

    //produces the label for an operator type
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
