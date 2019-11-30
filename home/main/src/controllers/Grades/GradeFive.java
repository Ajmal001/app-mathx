package main.src.controllers.Grades;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.src.controllers.Listeners.CommonPaneListener;
import main.src.controllers.WorkspaceExtras.SidePaneFactory;

/**
 * @author Karandeep Singh Grewal
 */

public class GradeFive implements GradeParent {

    @Override
    public void produceWorkspace(Pane sandBox, VBox sidePane, StackPane commonPane, String question) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();

        //adds one equation operator for grade 5
        sidePaneFactory.addLabelToSidePane(sidePane, "Equation");
        String[] equationOperators = {""};
        for (String operator : equationOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Equation", 2, commonPane);
        }

        //adds GCD operator for grade 5
        sidePaneFactory.addLabelToSidePane(sidePane, "Unary");
        String[] unaryOperators = {"gcd"};
        for (String operator : unaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Unary", 8, commonPane);
        }

        //adds algebric operators for grade 5
        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-", "*", "/", "^"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary", 8, commonPane);
        }

        //produces the common question and result pane for grade 5
        CommonPaneListener commonPaneListener = new CommonPaneListener();
        commonPaneListener.produceCommonPane(commonPane, question);
        commonPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(sandBox, commonPane);
            }
        });


    }
}
