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

public class GradeOne implements GradeParent {

    @Override
    public void produceWorkspace(Pane sandBox, VBox sidePane, StackPane resultOrQuestionPane) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();

        sidePaneFactory.addLabelToSidePane(sidePane, "Number");
        String[] numberOperators = {"1", "2"};
        for (String operator : numberOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Number", 1);
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Counter");
        String[] counterOperators = {"111"};
        for (String operator : counterOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Counter", 1);
        }


        sidePaneFactory.addLabelToSidePane(sidePane, "Unary");
        String[] unaryOperators = {"One"};
        for (String operator : unaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Unary", 1);
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary", 1);
        }


        CommonPaneListener commonPaneListener = new CommonPaneListener();
        String question = "What is the value of 2 + 4 ?";
        commonPaneListener.produceCommonPane(resultOrQuestionPane, question);
        resultOrQuestionPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(resultOrQuestionPane);
            }
        });


    }
}
