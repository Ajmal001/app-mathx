package main.src.controllers.Grades;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Karandeep Singh Grewal
 */

//Parent Class
public interface GradeParent {
    void produceWorkspace(Pane sandbox, VBox expressionPane, StackPane resultOrQuestionPane, String question);
}
