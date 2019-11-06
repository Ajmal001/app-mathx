package main.src.controllers.Grades;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.src.controllers.SidePaneFactory;

public class GradeOne implements GradeParent {

    @Override
    public void produceWorkspace(Pane sandBox, VBox expressionPane) {
        SidePaneFactory sidePaneFactory = new SidePaneFactory();
    }
}
