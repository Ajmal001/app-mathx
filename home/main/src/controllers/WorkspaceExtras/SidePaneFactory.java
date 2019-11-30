package main.src.controllers.WorkspaceExtras;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.src.controllers.Listeners.SandBoxListeners;
import main.src.controllers.Listeners.SidePaneListeners;
import main.src.controllers.Operator.*;

/**
 * @author Karandeep Singh Grewal
 */


public class SidePaneFactory {

    //adds label to the side pane
    public void addLabelToSidePane(VBox sidePane, String labelName) {
        ParentOperator operator = null;
        switch (labelName) {
            case "Unary": {
                operator = new UnaryOperator();
                break;
            }
            case "Binary": {
                operator = new BinaryOperator();
                break;
            }
            case "Compare": {
                operator = new CompareOperator();
                break;
            }
            case "Equation": {
                operator = new EquationOperator();
                break;
            }

        }
        assert operator != null;
        Pane label = operator.produceLabel();
        sidePane.getChildren().addAll(label);
    }

    //adds operators to the side pane
    public void addOperatorToSidePane(Pane sandBox, VBox sidePane, String string, String operatorType, int grade, StackPane commonPane) {
        SidePaneListeners sidePaneListeners = new SidePaneListeners();

        ParentOperator operator;
        switch (operatorType) {
            case "Unary": {
                operator = new UnaryOperator();
                break;
            }
            case "Binary": {
                operator = new BinaryOperator();
                break;
            }
            case "Compare": {
                operator = new CompareOperator();
                break;
            }
            case "Equation": {
                operator = new EquationOperator();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + operatorType);
        }
        StackPane stackPane;
        stackPane = operator.produceOperator(string, commonPane);
        sidePane.getChildren().addAll(stackPane);

        stackPane.setOnMouseClicked(e -> {
            SandBoxListeners sandBoxListeners = new SandBoxListeners();
            StackPane newStackPane;
            newStackPane = new StackPane(operator.produceOperator(string, commonPane));
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(8.0);
            dropShadow.setOffsetY(3);
            dropShadow.setColor(Color.color(0.5, 0.5, 0.5));
            newStackPane.setEffect(dropShadow);
            sandBoxListeners.makeDraggable(newStackPane, commonPane);
            sandBoxListeners.makeDeletable(newStackPane);
            sandBox.getChildren().addAll(newStackPane);
        });
    }

}
