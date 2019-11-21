package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.src.controllers.Grades.GradeFive;
import main.src.controllers.Grades.GradeParent;
import main.src.controllers.Grades.GradeTwo;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Karandeep Singh Grewal
 */


public class WorkspaceController implements Initializable {

    @FXML
    private Pane sandBox;
    @FXML
    public StackPane commonPane;
    @FXML
    private VBox sidePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int studentGrade = 2;
        GradeParent grade = null;
        switch (studentGrade) {
            case 2:
                grade = new GradeTwo();
                break;
            case 5:
                grade = new GradeFive();
                break;
            default:
                System.out.println("Unknown Grade");
                break;

        }
        System.out.println("Producing Grade:" + studentGrade);
        grade.produceWorkspace(sandBox, sidePane, commonPane);

    }

}