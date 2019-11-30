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

import java.util.Random;

/**
 * @author Karandeep Singh Grewal
 */

public class CompareOperator implements ParentOperator {
    @Override
    //produces the operator to compare two numbers as > or < or =
    public StackPane produceOperator(String operatorString, StackPane commonPane) {
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        float shapeWidth = 200;

        Rectangle rectangle = new Rectangle(shapeWidth, 50);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setFill(Paint.valueOf("#007AFF"));
        rectangle.setStroke(Paint.valueOf("#007AFF"));
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);

        HBox hBox = new HBox();
        hBox.setMinWidth(shapeWidth);
        hBox.setAlignment(Pos.CENTER);

        Label number1 = new Label(operatorString);
        number1.setMinWidth(40);
        number1.setAlignment(Pos.CENTER);
        number1.setStyle("-fx-font-size: 14");
        number1.setTextFill(Color.WHITE);

        TextField input = new TextField();
        input.setMinWidth(30);
        input.setPrefWidth(30);
        input.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: BOLD");

        final int LIMIT = 1;
        input.lengthProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (input.getText().length() >= LIMIT) {
                    input.setText(input.getText().substring(0, LIMIT));
                }
            } else if (input.getText().length() < LIMIT)
                rectangle.setWidth(190);
        });

        Label number2 = new Label(operatorString);
        number2.setMinWidth(40);
        number2.setAlignment(Pos.CENTER);
        number2.setStyle("-fx-font-size: 14");
        number2.setTextFill(Color.WHITE);
        hBox.getChildren().addAll(number1, input, number2);
        //random numbers are put into the compare operator
        number1.setText(String.valueOf(new Random().nextInt(50)));
        number2.setText(String.valueOf(new Random().nextInt(50)));

        stackPane.getChildren().addAll(rectangle, hBox);
        return stackPane;
    }

    @Override
    public StackPane produceLabel() {
        StackPane labelPane = new StackPane();
        labelPane.setPrefSize(200, 30);
        Label label = new Label("Compare");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        labelPane.getChildren().addAll(label);
        return labelPane;
    }
}
