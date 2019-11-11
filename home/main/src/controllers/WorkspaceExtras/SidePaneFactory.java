package main.src.controllers.WorkspaceExtras;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.src.controllers.Listeners.SandBoxListeners;
import main.src.controllers.Listeners.SidePaneListeners;
import main.src.controllers.Operator.BinaryOperator;
import main.src.controllers.Operator.Operator;
import main.src.controllers.Operator.UnaryOperator;

public class SidePaneFactory {

    public void addLabelToSidePane(VBox sidePane, String labelName) {
        Operator operator = null;
        switch (labelName) {
            case "Unary": {
                operator = new UnaryOperator();
                break;
            }
            case "Binary": {
                operator = new BinaryOperator();
                break;
            }
        }
        Pane unaryLabel = operator.produceLabel();
        sidePane.getChildren().addAll(unaryLabel);
    }

    public void addOperatorToSidePane(Pane sandBox, VBox sidePane, String string, String operatorType) {
        SidePaneListeners sidePaneListeners = new SidePaneListeners();

        Operator operator;
        switch (operatorType) {
            case "Unary": {
                operator = new UnaryOperator();
                break;
            }
            case "Binary": {
                operator = new BinaryOperator();
                break;
            }
            default:
                operator = new BinaryOperator();
        }

        StackPane stackPane;
        stackPane = operator.produceOperator(string);
        sidePane.getChildren().addAll(stackPane);

        stackPane.setOnMouseClicked(e -> {
            SandBoxListeners sandBoxListeners = new SandBoxListeners();
            StackPane newStackPane;
            newStackPane = new StackPane(operator.produceOperator(string));
            sandBoxListeners.makeDraggable(newStackPane);
            sandBox.getChildren().addAll(newStackPane);
        });
    }

}
