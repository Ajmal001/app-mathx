package main.src.controllers;
/**
 * Title:		 Math-X Application
 * Description:  CSE515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author
 * @version 	 1.0
 * @since        8/30/2019
 * @modified     11/3/2019
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;



/**
 * The Class WorkspaceController.
 */
public class WorkspaceController implements Initializable{

    /** The home button. */
    @FXML
    private Label homeButton;
    
    /** The expression pane. */
    @FXML
    private VBox expressionPane;

    /**
     * The Enum expressionType.
     */
    public enum expressionType{
        
        /** The single. */
        SINGLE,
        
        /** The double. */
        DOUBLE,
        
        /** The triple. */
        TRIPLE
    }

    /**
     * Initialize.
     *
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Expression expression=ExpressionFactory.produceExpressionType(expressionType.SINGLE,expressionPane);
        expression.produceExpressionType(expressionPane);

        Expression expression1=ExpressionFactory.produceExpressionType(expressionType.DOUBLE,expressionPane);
        expression1.produceExpressionType(expressionPane);
    }
}