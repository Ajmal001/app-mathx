package main.src.controllers.Operator;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

/**
 * @author Karandeep Singh Grewal
 */

public class EquationOperator implements ParentOperator {
    @Override
    //produces the operator of equation type - solves most of the equation inputs
    public StackPane produceOperator(String operatorString, StackPane commonPane) {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(200, 50);
        stackPane.setAlignment(Pos.CENTER);

        float shapeWidth = 150;

        Rectangle rectangle = new Rectangle(shapeWidth, 50);
        rectangle.setArcHeight(50);
        rectangle.setArcWidth(50);
        rectangle.setFill(Paint.valueOf("#248A3D"));
        rectangle.setStroke(Paint.valueOf("#248A3D"));
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);

        HBox hBox = new HBox();
        hBox.setMinWidth(shapeWidth);
        hBox.setAlignment(Pos.CENTER);

        TextField input = new TextField();
        input.setMinWidth(60);
        input.setPrefWidth(60);
        input.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: BOLD");

        input.textProperty().addListener((ov, prevText, currText) -> Platform.runLater(() -> {
            Text text = new Text(currText);
            text.setFont(input.getFont());
            double width = text.getLayoutBounds().getWidth()
                    + input.getPadding().getLeft() + input.getPadding().getRight()
                    + 2d;
            if (width < 30)
                rectangle.setWidth(190);
            input.setPrefWidth(width);
            input.positionCaret(input.getCaretPosition());
            rectangle.setWidth(110 + input.getWidth());
        }));


        hBox.getChildren().addAll(input);
        stackPane.getChildren().addAll(rectangle, hBox);
        return stackPane;
    }


    @Override
    public StackPane produceLabel() {
        StackPane labelPane = new StackPane();
        labelPane.setPrefSize(200, 30);
        Label label = new Label("");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 16");
        labelPane.getChildren().addAll(label);
        return labelPane;
    }
}
