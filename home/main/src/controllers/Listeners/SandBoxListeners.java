package main.src.controllers.Listeners;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.awt.*;

public class SandBoxListeners {

    public void setNumber(TextField number, Rectangle rectangle) {
        number.textProperty().addListener((ov, prevText, currText) -> {        // Code Reuse https://bit.ly/314SAz0
            Platform.runLater(() -> {
                Text text = new Text(currText);
                text.setFont(number.getFont());
                double width = text.getLayoutBounds().getWidth()
                        + number.getPadding().getLeft() + number.getPadding().getRight()
                        + 2d;
//                if (width<30)
//                    rectangle.setWidth(190);
                number.setPrefWidth(width);
                number.positionCaret(number.getCaretPosition());
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
//                    rectangle.setWidth(190);
                    System.out.println("Rectangle Length");
            }
        });
    }

    public void makeDraggable(StackPane operator) {
        //Sandbox Bounds
        int HorizontalBound = 1176;
        int VerticalBound = 730;

        operator.setOnMouseEntered(mouseEvent -> operator.setCursor(Cursor.MOVE));

        //Adjust drag for instant drag
        final double[] deltaX = new double[1];
        final double[] deltaY = new double[1];
        operator.setOnMouseMoved(mouseEvent -> {
            deltaX[0] = operator.getLayoutX() - mouseEvent.getSceneX();
            deltaY[0] = operator.getLayoutY() - mouseEvent.getSceneY();
        });


        //No drag out of bounds
        operator.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.getSceneX() < -deltaX[0]) {
                operator.setLayoutX(0);
            }
            //Right
            else if (mouseEvent.getSceneX() + operator.getWidth() + deltaX[0] > HorizontalBound) {
                operator.setLayoutX(HorizontalBound - operator.getWidth());
            }
            //Horizontal - In bounds
            else {
                operator.setLayoutX(mouseEvent.getSceneX() + deltaX[0]);
            }
            //Top
            if (mouseEvent.getSceneY() < -deltaY[0]) {
                operator.setLayoutY(1);
            }
            //Bottom
            else if (mouseEvent.getSceneY() + operator.getHeight() + deltaY[0] > VerticalBound) {
                operator.setLayoutY(VerticalBound - operator.getHeight());
            }
            //Vertical - In bounds
            else {
                operator.setLayoutY(mouseEvent.getSceneY() + deltaY[0]);
            }
            StackPane dummy;
            dummy = (StackPane) operator.getChildren().get(0);
            HBox dummyBox = (HBox) dummy.getChildren().get(1);
        });

    }

}
