package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  CSE515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author 		 Grewal Karandeep Singh
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;


/**
 * The Class OperatorsBinary.
 */
public class OperatorsBinary implements Expression{

    /**
     * Produce expression type.
     *
     * @param vBox the v box
     */
    @Override
    public void produceExpressionType(VBox vBox) {
        Pane labelPane = new Pane();

        labelPane.setPrefSize(200,100);
        Label label=new Label("Double Input Expressions");
        labelPane.getChildren().addAll(label);

        Rectangle rectangle=new Rectangle(170,40);

        vBox.getChildren().addAll(labelPane,rectangle);

    }

    /**
     * Produce shape.
     *
     * @param s the s
     * @return the stack pane
     */
    @Override
    public StackPane produceShape(String s) {
        return null;
    }


    /**
     * Produce label pane.
     *
     * @return the pane
     */
    @Override
    public Pane produceLabelPane() {
        return null;
    }
}
