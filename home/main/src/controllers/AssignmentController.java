package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import main.MainClass;
import main.src.models.AssignmentModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class AssignmentController implements Initializable {

    @FXML
    private ListView<String> listBoxMain;

    @FXML
    private VBox VBoxMain;

    static String asgn;

    public static AssignmentModel assignmentModel = new AssignmentModel();

    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(" ");
        alert.setHeaderText(null);
        //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Create assignment action.
     *
     * @param actionEvent the action event
     */

    @FXML
    void create(ActionEvent actionEvent) {

        new MainClass().create_assignmentWindow();
        MainClass.asgnStage.close();

    }

    /**
     * Initialize.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    ObservableList<String> assign = FXCollections.observableArrayList(displayAssignments());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listBoxMain.setItems(assign);
        listBoxMain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println(t1);
                AssignmentController.asgn = t1;
                new MainClass().view_assignmentWindow();

            }
        });
    }

    List<String> displayAssignments() {
        CountDownLatch done = new CountDownLatch(1);
        final String[] message = {null};
        List<String> assignmentlist = new ArrayList<>();
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Assignment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    assignmentModel = data.getValue(AssignmentModel.class);
                    assignmentModel.setId(data.getKey());
                    assignmentlist.add(assignmentModel.getAssignmentName());
                    //System.out.println("Size:" + assignmentlist.size());
                }
                done.countDown();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        try {
            done.await(); //it will wait till the response is received from firebase.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return assignmentlist;
    }

    public static void main(String[] args) {
        AssignmentController as = new AssignmentController();
        as.displayAssignments();
    }
}
