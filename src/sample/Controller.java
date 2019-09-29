package sample;

import com.firebase.client.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;

    @FXML
    void registerAction(ActionEvent actionEvent)
    {
        Firebase firebase=new Firebase("https://mathx-eea50.firebaseio.com/");
        firebase.child("teacher_name").setValue("Manas");


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

