package main.src.controllers.Operator;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BinaryOperator implements Operator {
    @Override
    public StackPane produceOperator(String operatorString) {
        StackPane stackPane = new StackPane();

        stackPane.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return stackPane;
    }

    @Override
    public Pane produceLabel() {
        return null;
    }
}
