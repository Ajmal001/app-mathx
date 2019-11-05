package main.src.controllers;

import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;

class SandBoxListeners {

    void drag(StackPane operator) {

        final double[] deltaX = new double[1];
        final double[] deltaY = new double[1];

        operator.setOnMouseEntered(mouseEvent -> operator.setCursor(Cursor.MOVE));

        operator.setOnMouseMoved(mouseEvent -> {
            deltaX[0] = operator.getLayoutX() - mouseEvent.getSceneX();
            deltaY[0] = operator.getLayoutY() - mouseEvent.getSceneY();
        });

        operator.setOnMouseDragged(mouseEvent -> {
            //Left X-Bound
            if (mouseEvent.getSceneX() < -deltaX[0]) {
                operator.setLayoutX(1);
            }
            //Right X-Bound
            else if (mouseEvent.getSceneX() + operator.getWidth() + deltaX[0] > 1365) {
                operator.setLayoutX(1365 - operator.getWidth());
            } else {
                operator.setLayoutX(mouseEvent.getSceneX() + deltaX[0]);
            }
            //Upper Y-Bound
            if (mouseEvent.getSceneY() < -deltaY[0]) {
                operator.setLayoutY(1);
            }
            //Lower Y-Bound
            else if (mouseEvent.getSceneY() + operator.getHeight() + deltaY[0] > 660) {
                operator.setLayoutY(660 - operator.getHeight());
            } else {
                operator.setLayoutY(mouseEvent.getSceneY() + deltaY[0]);  /*190,110*/ /*232,220*/
            }
        });
    }
}
