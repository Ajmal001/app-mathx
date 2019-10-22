package main.src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class workspaceController implements Initializable {

    private StackPane newRectangle(){

        Rectangle shape=new Rectangle(190,70);
        shape.setArcWidth(30.0);
        shape.setArcHeight(30.0);
        shape.setStroke(Color.WHITE);
        shape.setFill(Color.color(Math.random(),Math.random(),Math.random()));

        MenuItem menuItem1a = new MenuItem("1");
        MenuItem menuItem2a= new MenuItem("2");
        MenuItem menuItem3a = new MenuItem("3");
        MenuButton numberOne=new MenuButton("1",null,menuItem1a,menuItem2a,menuItem3a);

        MenuItem menuItem1b = new MenuItem("+");
        MenuItem menuItem2b = new MenuItem("-");
        MenuItem menuItem3b = new MenuItem("/");
        MenuButton operator=new MenuButton("+",null,menuItem1b,menuItem2b,menuItem3b);

        MenuItem menuItem1c = new MenuItem("1");
        MenuItem menuItem2c = new MenuItem("2");
        MenuItem menuItem3c = new MenuItem("3");
        MenuButton numberTwo=new MenuButton("1",null,menuItem1c,menuItem2c,menuItem3c);


        HBox shapeHBox=new HBox(numberOne,operator,numberTwo);
        shapeHBox.setAlignment(Pos.CENTER);
        shapeHBox.setSpacing(10);

//        StackPane shapeStack=new StackPane(shape,shapeHBox);


        return new StackPane(shape,shapeHBox);
    }

    @FXML
    private Pane practiceSection;

    public void setPracticeSection(Pane practiceSection) {
        this.practiceSection = practiceSection;
        practiceSection.setPadding(new Insets(20,20,20,20));
    }

    @FXML
    private VBox operatorSection;
    private Text source = new Text(50, 100, "DRAG ME");
    private Text target = new Text(300, 100, "DROP HERE");

    int a=0,j=0,i=0;

    private void setOperatorSection(VBox operatorSection) {
        this.operatorSection = operatorSection;
        operatorSection.setAlignment(Pos.BASELINE_CENTER);
        StackPane equation1=newRectangle();
        operatorSection.getChildren().add(equation1);
        //equation1.setOnMouseClicked(mouseEvent -> {
//            practiceSection.getChildren().add(newRectangle());
//            practiceSection.getChildren().get(a).relocate(i,j);
//            i+=190;
//            if(i>4*190){
//                i=0;
//                j+=90;
//            }
//            a++;
//        });
        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            db.setContent(content);

            event.consume();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        practiceSection.getChildren().addAll(source,target);
        setOperatorSection(operatorSection);
    }
}