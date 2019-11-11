package main.src.controllers.Grades;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.src.controllers.SidePaneFactory;

public class GradeTwo implements GradeParent {


    @Override
    public void produceWorkspace(Pane sandBox, VBox sidePane) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();
        sidePaneFactory.addLabelToSidePane(sidePane, "Unary");
        String[] unaryOperators = {"log", "lg", "sin", "cos"};
        for (String operator : unaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Unary");
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-", "*"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary");
        }

    }
}
