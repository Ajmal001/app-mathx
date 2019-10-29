package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
//
//    private void addToExpressionPane(VBox expressionType){
//        expressionPane.getChildren().addAll((expressionType));
//    }
//
//    private VBox expressionTypeFactory(int inputs) {
//        VBox expression=new VBox();
//        if (inputs == 1) {
//            expression = createSingleInputExpression();
//        }
//        return expression;
//    }
//
//
//    private VBox createSingleInputExpression(){
//        VBox expression=new VBox();
//
//        return expression;
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Expression expression = ExpressionFactory.produceExpression(expressionType.SINGLE);
//        if(expression!=null)
//            expression.produceExpression();
//        else
//            System.out.println("Couldn't Draw Single");
//        Expression expression1 = ExpressionFactory.produceExpression(expressionType.DOUBLE);
//        if(expression1!=null)
//            expression1.produceExpression();
//        else
//            System.out.println("Couldn't Draw Double");
//

    }
}