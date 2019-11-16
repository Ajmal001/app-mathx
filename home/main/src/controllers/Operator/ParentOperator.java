package main.src.controllers.Operator;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public interface ParentOperator {

    StackPane produceOperator(String operatorString);

    Pane produceLabel();
}
