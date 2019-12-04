package main.src.controllers.Operator;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Karandeep Singh Grewal
 */

//parent operator factory
public interface ParentOperator {

    StackPane produceOperator(String operatorString, StackPane commonPane);

    Pane produceLabel();
}
