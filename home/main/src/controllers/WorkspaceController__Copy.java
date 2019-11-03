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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;



/**
 * The Class WorkspaceController__Copy.
 */
public class WorkspaceController__Copy implements Initializable {

    /** The rectangle. */
    @FXML
    private Rectangle rectangle;

    /** The number 1. */
    @FXML
    private TextField number1;
    
    /** The number 2. */
    @FXML
    private TextField number2;

    /**
     * Sets the operator.
     *
     * @param operator the new operator
     */
    private void setOperator(StackPane operator) {

        final double[] deltaX = new double[1];
        final double[] deltaY = new double[1];
        operator.setOnMouseEntered(mouseEvent -> operator.setCursor(Cursor.MOVE));
        operator.setOnMouseMoved(mouseEvent -> {
            deltaX[0]=operator.getLayoutX()-mouseEvent.getSceneX();
            deltaY[0]=operator.getLayoutY()-mouseEvent.getSceneY();
        });
        operator.setOnMouseDragged(mouseEvent -> {
            //Left X-Bound
            if(mouseEvent.getSceneX()<-deltaX[0]){
                operator.setLayoutX(1);
            }
            //Right X-Bound
            else if(mouseEvent.getSceneX()+operator.getWidth()+deltaX[0]>1365){
                operator.setLayoutX(1365-operator.getWidth());
            }
            else {
                operator.setLayoutX(mouseEvent.getSceneX() + deltaX[0]);
            }
            //Upper Y-Bound
            if(mouseEvent.getSceneY()<-deltaY[0]){
                operator.setLayoutY(1);
            }
            //Lower Y-Bound
            else if(mouseEvent.getSceneY()+operator.getHeight()+deltaY[0]>660){
                operator.setLayoutY(660-operator.getHeight());
            }
            else {
                operator.setLayoutY(mouseEvent.getSceneY() + deltaY[0]);  /*190,110*/ /*232,220*/
            }
        });

    }


    /**
     * Sets the number.
     *
     * @param number the new number
     */
    private void setNumber(TextField number){
        number.textProperty().addListener((ov, prevText, currText) -> {        // Code Reuse https://bit.ly/314SAz0
            Platform.runLater(() -> {
                Text text = new Text(currText);
                text.setFont(number.getFont());
                double width = text.getLayoutBounds().getWidth()
                        + number.getPadding().getLeft() + number.getPadding().getRight()
                        + 2d;
                if (width<30)
                    rectangle.setWidth(190);
                number.setPrefWidth(width);
                number.positionCaret(number.getCaretPosition());
                setRectangle(rectangle, (int) width);
            });
        });
        number.lengthProperty().addListener(new ChangeListener<>() {
            final int LIMIT = 10;

            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (number.getText().length() >= LIMIT) {
                        number.setText(number.getText().substring(0, LIMIT));
                    }
                } else if (number.getText().length() < LIMIT)
                    rectangle.setWidth(190);
            }
        });
    }

    /**
     * Sets the rectangle.
     *
     * @param rectangle the rectangle
     * @param width the width
     */
    private void setRectangle(Rectangle rectangle, int width) {
        this.rectangle = rectangle;
        rectangle.setWidth(110+number2.getWidth()+number1.getWidth());
    }

    /**
     * Initialize.
     *
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setOperator(operator);
//        setNumber(number1);
//        setNumber(number2);
    }
}