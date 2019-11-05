package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.src.controllers.Operator.Operator;
import main.src.controllers.Operator.UnaryOperator;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkspaceController implements Initializable {

    @FXML
    private Pane sandBox;
    @FXML
    private Label homeButton;
    @FXML
    private VBox expressionPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SidePaneListeners sidePaneListeners = new SidePaneListeners();

        //Creating another operator
        Operator operator1 = new UnaryOperator();
        StackPane stackPane1;
        stackPane1 = operator1.produceOperator();

        //Adding operator to sidepane
        expressionPane.getChildren().addAll(stackPane1);
        sidePaneListeners.installToolTip(stackPane1);

        SandBoxListeners sandBoxListeners = new SandBoxListeners();

        //Creating Unary Operator
        Operator operator = new UnaryOperator();
        StackPane stackPane = new StackPane(operator.produceOperator());

        //Adding operator to sandbox
        sandBox.getChildren().addAll(stackPane);
        sandBoxListeners.drag(stackPane);


    }

    public enum expressionType {
        UNARY,
        BINARY
    }
}