package main.src.controllers;

import javafx.scene.layout.VBox;

public abstract class ExpressionFactory {

    public static Expression produceExpressionType(WorkspaceController.expressionType expressionType, VBox vBox){
        Expression expression=null;
        switch (expressionType){
            case SINGLE: {
                expression=new OperatorsUnary();
                break;
            }
            case DOUBLE:{
                expression=new OperatorsBinary();
                break;
            }
            case TRIPLE:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + expressionType);
        }
        return expression;
    }
}
