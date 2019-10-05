package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class WorkspaceController implements Initializable {

    @FXML
    private Pane practiceSection;
    @FXML
    private VBox operatorSection;
    @FXML
    private Label resetButton;

    private StackPane newRectangle() {

        Rectangle shape = new Rectangle(190, 70);
        shape.setArcWidth(30.0);
        shape.setArcHeight(30.0);
        shape.setStroke(Color.WHITE);
        shape.setFill(Color.color(Math.random(), Math.random(), Math.random()));


        MenuItem menuItem1a = new MenuItem("1");
        MenuItem menuItem2a = new MenuItem("2");
        MenuItem menuItem3a = new MenuItem("3");
        MenuItem menuItem4a = new MenuItem("4");
        MenuItem menuItem5a = new MenuItem("5");
        MenuItem menuItem6a = new MenuItem("6");
        MenuItem menuItem7a = new MenuItem("7");
        MenuItem menuItem8a = new MenuItem("8");
        MenuItem menuItem9a = new MenuItem("9");

        MenuButton numberOne = new MenuButton("1", null, menuItem1a, menuItem2a, menuItem3a,menuItem4a,menuItem5a,menuItem6a ,menuItem7a,menuItem8a,menuItem9a);

        MenuItem menuItem1b = new MenuItem("+");
        MenuItem menuItem2b = new MenuItem("-");
        MenuItem menuItem3b = new MenuItem("/");
        MenuButton operator = new MenuButton("+", null, menuItem1b, menuItem2b, menuItem3b);

        MenuItem menuItem1c = new MenuItem("1");
        MenuItem menuItem2c = new MenuItem("2");
        MenuItem menuItem3c = new MenuItem("3");

        MenuButton numberTwo = new MenuButton("1", null, menuItem1c, menuItem2c, menuItem3c);


        HBox shapeHBox = new HBox(numberOne, operator, numberTwo);
        shapeHBox.setAlignment(Pos.CENTER);
        shapeHBox.setSpacing(10);

        return new StackPane(shape, shapeHBox);
    }


    public void setPracticeSection(Pane practiceSection) {
        this.practiceSection = practiceSection;
        practiceSection.setPadding(new Insets(20, 20, 20, 20));

        StackPane sampleDrag = newRectangle();
        practiceSection.getChildren().add(sampleDrag);


    }

    int a = 0, j = 0, i = 0;

    private void setOperatorSection(VBox operatorSection) {
        this.operatorSection = operatorSection;
        operatorSection.setAlignment(Pos.BASELINE_CENTER);
        StackPane equation1 = newRectangle();
        operatorSection.getChildren().add(equation1);
        equation1.setOnMouseClicked(mouseEvent -> {
            practiceSection.getChildren().add(newRectangle());
            practiceSection.getChildren().get(a).relocate(i, j);
            i += 190;
            if (i > 4 * 190) {
                i = 0;
                j += 70;
            }
            a++;
        });
        final double[] deltaX = new double[1];
        final double[] deltaY = new double[1];


        try {

            deltaX[0]=0;
            deltaY[0]=0;
            practiceSection.getChildren().get(0).setOnMouseClicked(mouseEvent -> {
                deltaX[0] = practiceSection.getChildren().get(0).getLayoutX() - mouseEvent.getSceneX();
                deltaY[0] = practiceSection.getChildren().get(0).getLayoutY() - mouseEvent.getSceneY();
                System.out.println(deltaX[0]);System.out.println(deltaY[0]);

            });

            practiceSection.getChildren().get(0).setOnMouseReleased(mouseEvent -> {
                practiceSection.getChildren().get(0).setCursor(Cursor.HAND);
            });


            practiceSection.getChildren().get(0).setOnMouseEntered(mouseEvent -> {
                practiceSection.getChildren().get(0).setCursor(Cursor.MOVE);
            });

            practiceSection.getChildren().get(0).setOnMouseDragged(mouseEvent -> {
                practiceSection.getChildren().get(0).setLayoutX(mouseEvent.getSceneX() + deltaX[0]);
                practiceSection.getChildren().get(0).setLayoutY(mouseEvent.getSceneY() + deltaY[0]);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResetButton(Label resetButton) {
        this.resetButton = resetButton;
        resetButton.setOnMouseClicked(mouseEvent -> {
            practiceSection.getChildren().clear();
            i=0;j=0;a=0;
        });
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPracticeSection(practiceSection);
        setOperatorSection(operatorSection);
        setResetButton(resetButton);
    }



}