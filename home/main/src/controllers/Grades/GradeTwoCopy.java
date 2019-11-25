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

public class GradeTwoCopy implements GradeParent {

    @Override
    public void produceWorkspace(Pane sandBox, VBox sidePane, StackPane commonPane) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();

        sidePaneFactory.addLabelToSidePane(sidePane, "Compare");
        String[] comparison = {""};
        for (String operator : comparison) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Compare", 2, commonPane);
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary", 2, commonPane);
        }

        CommonPaneListener commonPaneListener = new CommonPaneListener();
        String question = "1. Two + Two? <TEXTFIELD> <PROMPT:Answer:PROMPT> <SEP>" +
                "\n2. Five - Three? <TEXTFIELD> <PROMPT:Answer:PROMPT>";

        commonPaneListener.produceCommonPane(commonPane, question);
        commonPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(sandBox, commonPane);
            }
        });


    }
}