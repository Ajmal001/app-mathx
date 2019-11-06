package main.src.controllers.Listeners;

import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;

public class SandBoxListeners {

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
        });
    }

}
