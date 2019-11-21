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
        sidePaneFactory.addLabelToSidePane(sidePane, "Unary");
        String[] unaryOperators = {"gcd", ""};
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
        String question = " Question Area";
        commonPaneListener.produceCommonPane(commonPane, question);
        commonPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(sandBox, commonPane);
            }
        });


    }
}
