package main.src.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.lang.model.type.NullType;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkspaceController implements Initializable {

    @FXML
    private VBox operatorSection;

    @FXML
    private Button addOperator;

    @FXML
    private Pane practiceSection;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}