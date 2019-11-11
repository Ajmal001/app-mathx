package main.src.controllers;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.src.controllers.Listeners.SandBoxListeners;
import main.src.controllers.Listeners.SidePaneListeners;
import main.src.controllers.Operator.Operator;
import main.src.controllers.Operator.UnaryOperator;

//Add to sidePane
public class SidePaneOperator {

    void addToSidePane(Pane sandBox, VBox expressionPane) {
        SidePaneListeners sidePaneListeners = new SidePaneListeners();
        Operator operator1 = new UnaryOperator();
        StackPane stackPane1;
        stackPane1 = operator1.produceOperator("+");
        Pane unaryLabel = operator1.produceLabel();
        expressionPane.getChildren().addAll(unaryLabel, stackPane1);
        stackPane1.setOnMouseClicked(e -> {
            SandBoxListeners sandBoxListeners = new SandBoxListeners();
            Operator operator = new UnaryOperator();
            StackPane stackPane = new StackPane(operator.produceOperator("+"));
            sandBoxListeners.makeDraggable(stackPane);
            sandBox.getChildren().addAll(stackPane);
            System.out.println(sandBox.getChildren());
        });
    }


}
