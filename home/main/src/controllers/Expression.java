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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;



/**
 * The Interface Expression.
 */
public interface Expression {
    
    /**
     * Produce expression type.
     *
     * @param vBox the v box
     */
    void produceExpressionType(VBox vBox);
    
    /**
     * Produce shape.
     *
     * @param s the s
     * @return the stack pane
     */
    StackPane produceShape(String s);
    
    /**
     * Produce label pane.
     *
     * @return the pane
     */
    Pane produceLabelPane();
}
