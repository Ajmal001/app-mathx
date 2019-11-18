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

public class GradeEight implements GradeParent {

    @Override
    public void produceWorkspace(Pane sandBox, VBox sidePane, StackPane commonPane) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();
        sidePaneFactory.addLabelToSidePane(sidePane, "Unary");
        String[] unaryOperators = {"log", "lg", "sin", ""};
        for (String operator : unaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Unary", 8, commonPane);
        }

        sidePaneFactory.addLabelToSidePane(sidePane, "Binary");
        String[] binaryOperators = {"+", "-", "*"};
        for (String operator : binaryOperators) {
            sidePaneFactory.addOperatorToSidePane(sandBox, sidePane, operator, "Binary", 8, commonPane);
        }
        CommonPaneListener commonPaneListener = new CommonPaneListener();
        //Change this string below to change the question
        String question = "1. Paul creates a scatter plot with a negative association. The x-axis of the scatter plot is titled,\\n\n" +
                "\"Minutes Spent at Mall\". Which label is most likely the title of the y-axis of Paul’s scatter plot?\\n\n" +
                "A. Distance Walked\n" +
                "B. Money Available to Spend\n" +
                "C. Number of Movies Seen\\n\n" +
                "D. Number of Stores Visited\n\n" +
                "2. The United States exported approximately 30,000,000 metric tons of wheat over an entire\n" +
                "year. What is the number of metric tons of wheat written in scientific notation?\n" +
                "A. 3 × 10 4\n" +
                "B. 3 × 10 5\n" +
                "C. 3 × 10 6\n" +
                "D. 3 × 10 7\n\n" +
                "3. Greg started with a certain number of quarters. He then decided on a number of quarters he\n" +
                "would save each day. He added the quarters he saved to the amount with which he started. At\n" +
                "the end of day 2, Greg had a total of 26 quarters saved. At the end of day 5, he had a total of\n" +
                "35 quarters saved.\n" +
                "A. How many quarters does Greg start with? Show or explain your work.\n" +
                "B. Write an equation to model the number of quarters Greg has saved, y, after x days.\n" +
                "C. Using the rate at which Greg is saving, explain why he can never have exactly\n" +
                "100 quarters saved by the end of any given day.\n" +
                "D. Starting with day 2 and going through day 20, Greg created a graph to show the relationship\n" +
                "between the day and the total number of quarters he had saved by the end of that day.\n" +
                "Greg plotted each of the 19 data points and then connected them. Describe what\n" +
                "Greg’s graph looked like.";
        commonPaneListener.produceCommonPane(commonPane, question);
        commonPane.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                commonPaneListener.switchPane(sandBox, commonPane);
            }
        });
        //Make Detachable


    }
}
