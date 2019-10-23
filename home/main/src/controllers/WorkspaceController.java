package main.src.controllers;

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


public class WorkspaceController implements Initializable {

    @FXML
    private StackPane operator;

    @FXML
    private Rectangle rectangle;

    @FXML
    private TextField number1;
    @FXML
    private TextField number2;


    private void setOperator(StackPane operator) {
        this.operator = operator;

        final double[] deltaX = new double[1];
        final double[] deltaY = new double[1];
        operator.setOnMouseEntered(mouseEvent -> operator.setCursor(Cursor.MOVE));
        operator.setOnMouseMoved(mouseEvent -> {
            deltaX[0]=operator.getLayoutX()-mouseEvent.getSceneX();
            deltaY[0]=operator.getLayoutY()-mouseEvent.getSceneY();
        });
        operator.setOnMouseDragged(mouseEvent -> {
            operator.setLayoutX(mouseEvent.getSceneX()+deltaX[0]);
            operator.setLayoutY(mouseEvent.getSceneY()+deltaY[0]);  /*190,110*/ /*232,220*/
        });

    }


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
            int LIMIT = 10;

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

    public void setRectangle(Rectangle rectangle,int width) {
        this.rectangle = rectangle;
        rectangle.setWidth(110+number2.getWidth()+number1.getWidth());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setOperator(operator);
        setNumber(number1);
        setNumber(number2);
    }
}