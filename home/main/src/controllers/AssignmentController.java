package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;
import javafx.scene.layout.VBox;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;
import javafx.scene.control.Label;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;
import javafx.scene.layout.VBox;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class AssignmentController implements Initializable {

    public static AssignmentModel assignmentModel = new AssignmentModel();

    @FXML
     static VBox asgn;

    private  static RadioButton radioButtonAssign;
    static ToggleGroup toggleGroup = new ToggleGroup();


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

    }

    /**
     * Initialize.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    List<String> displayAssignments() {

        CountDownLatch done = new CountDownLatch(1);
        final String message[] = {null};

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
        for (int i = 0; i < assignmentlist.size(); i++) {
            System.out.println(assignmentlist.get(i));
            System.out.println("====");
/*
             radioButtonAssign = new RadioButton();

            radioButtonAssign.setText("wqe");
            radioButtonAssign.setToggleGroup(toggleGroup);
            asgn.getChildren().addAll(radioButtonAssign);
*/
        }
        return assignmentlist;
    }


    public static void main(String[] args) {

        AssignmentController as = new AssignmentController();
        as.displayAssignments();

      //  List<String> qp=as.displayAssignments();
//        Label x=new Label();
//        Label b = new Label("B");
        //    asgn.getChildren().add((Node) qp);

        String s = "";
/*
        for (int i = 0; i < qp.size(); i++) {
            //x.setText(new Label(qp.get(i)));
         //   asgn.getChildren().add(b);
         //   asgn.getChildren().add(new Label(qp.get(i)));
            System.out.println(qp.get(i));
            s+=qp.get(i);
            s+='\n';
        }
        System.out.println(s);

        */

    }


}
