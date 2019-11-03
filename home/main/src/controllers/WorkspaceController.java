package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkspaceController implements Initializable{

    @FXML
    private Label homeButton;
    @FXML
    private VBox expressionPane;

    public enum expressionType{
        SINGLE,
        DOUBLE,
        TRIPLE
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Expression expression=ExpressionFactory.produceExpressionType(expressionType.SINGLE,expressionPane);
        expression.produceExpressionType(expressionPane);

        Expression expression1=ExpressionFactory.produceExpressionType(expressionType.DOUBLE,expressionPane);
        expression1.produceExpressionType(expressionPane);
    }
}