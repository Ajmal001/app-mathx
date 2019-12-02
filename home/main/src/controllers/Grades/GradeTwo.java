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

public class GradeTwo implements GradeParent {

    @Override
    public void produceWorkspace(Pane sandBox, VBox sidePane, StackPane commonPane, String question) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();

        //Adds one compare operator for grade 2
        sidePaneFactory.addLabelToSidePane(sidePane, "Compare");
        String[] comparison = {""};
        for (String operator : comparison) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Compare", 2, commonPane);
        }

        //adds addition and subtraction operator for grade 2
        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary", 2, commonPane);
        }

        //adds the result and question pane for grade 2
        CommonPaneListener commonPaneListener = new CommonPaneListener();
        commonPaneListener.produceCommonPane(commonPane, question);
        commonPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(sandBox, commonPane);
            }
        });


    }
}
