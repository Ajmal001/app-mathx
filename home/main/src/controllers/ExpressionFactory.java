package main.src.controllers;

import javafx.scene.layout.VBox;

public abstract class ExpressionFactory {

    public static Expression produceExpression(WorkspaceController.expressionType expressionType,VBox vBox){
        Expression expression=null;
        switch (expressionType){
            case SINGLE: {
                expression=new SingleInputExpression();
                break;
            }
            case DOUBLE:{
                expression=new SingleInputExpression();
                break;
            }
        }
        return expression;
    }
}
