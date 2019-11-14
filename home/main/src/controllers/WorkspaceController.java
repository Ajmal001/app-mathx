package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.src.controllers.Grades.GradeOne;
import main.src.controllers.Grades.GradeParent;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkspaceController implements Initializable {

    @FXML
    private Pane sandBox;
    @FXML
    public StackPane commonPane;
    @FXML
    private VBox sidePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GradeParent grade = new GradeOne();
//        GradeParent grade = new GradeTwo();
        grade.produceWorkspace(sandBox, sidePane, commonPane);

    }

    public enum operatorType {
        UNARY,
        BINARY
    }
}