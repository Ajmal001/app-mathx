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
    public void produceWorkspace(Pane sandBox, VBox sidePane, StackPane commonPane) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();

        sidePaneFactory.addLabelToSidePane(sidePane, "Equation");
        String[] equationOperators = {""};
        for (String operator : equationOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Equation", 2, commonPane);
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Unary");
        String[] unaryOperators = {"gcd"};
        for (String operator : unaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Unary", 8, commonPane);
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-", "*", "/", "^"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary", 8, commonPane);
        }
        CommonPaneListener commonPaneListener = new CommonPaneListener();
        //Change this string below to change the question
        String question = "\n3. Two wires are 12m and 16m long. The wires are to be cut into pieces of equal length. Find the maximum length of each piece." +
                "\n  If you use 4 wires of the resultant length, what will be the area of shape formed? ";


        commonPaneListener.produceCommonPane(commonPane, question);
        commonPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(sandBox, commonPane);
            }
        });


    }
}
