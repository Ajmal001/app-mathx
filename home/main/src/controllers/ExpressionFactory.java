package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author 		 Mahapatra Manas
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */
import javafx.scene.layout.VBox;


/**
 * A factory for creating Expression objects.
 */
public abstract class ExpressionFactory {

    /**
     * Produce expression type.
     *
     * @param expressionType the expression type
     * @param vBox the v box
     * @return the expression
     */
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
