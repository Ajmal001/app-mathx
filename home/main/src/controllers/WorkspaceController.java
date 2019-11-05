package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

//
//        //SIDEPANE GENERATION
//        SidePaneListeners sidePaneListeners = new SidePaneListeners();
//
//        //Creating unary operator
//        Operator operator1 = new UnaryOperator();
//        StackPane stackPane1;
//        stackPane1 = operator1.produceOperator();
//
//        //Creating Unary Label
//        Pane unaryLabel = operator1.produceLabel();
//
//        //Installing Toolkit
//        sidePaneListeners.installToolTip(stackPane1);
//
//        //Adding label and operator to sidePane
//        expressionPane.getChildren().addAll(unaryLabel,stackPane1);
//
//
//
//        //SANDBOX GENERATION
//        SandBoxListeners sandBoxListeners = new SandBoxListeners();
//
//        //Creating Unary Operator
//        Operator operator = new UnaryOperator();
//        StackPane stackPane = new StackPane(operator.produceOperator());
//
//        //Adding Drag
//        sandBoxListeners.makeDraggable(stackPane);
//
//        //Adding operator to sandbox
//        sandBox.getChildren().addAll(stackPane);



    }

    public enum expressionType {
        UNARY,
        BINARY
    }
}