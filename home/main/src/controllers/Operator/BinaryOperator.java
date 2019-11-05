package main.src.controllers.Operator;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class BinaryOperator implements Operator {
    @Override
    public StackPane produceOperator() {
        StackPane stackPane = new StackPane();

        stackPane.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return stackPane;
    }
}
