package main.src.controllers;

import javafx.scene.layout.VBox;

public abstract class OperatorFactory {

    public static Operator produceExpressionType(WorkspaceController.expressionType expressionType) {
        Operator operator = null;
        switch (expressionType) {
            case SINGLE: {
                operator = new OperatorsUnary();
                break;
            }
            case DOUBLE: {
                operator = new OperatorsBinary();
                break;
            }
            case TRIPLE:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + expressionType);
        }
        return operator;
    }
}
