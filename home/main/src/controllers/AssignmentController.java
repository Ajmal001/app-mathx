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
import javafx.scene.control.ListView;
import main.MainClass;
import main.src.models.AssignmentModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

/**
 * Title:		 Math-X Application
 * Description:  SER 515 Project
 * Copyright:    Copyright (c) 2019
 * Company:      Department of Computer Software Engineering, Arizona State University.
 *
 * @author Mahapatra Manas, Bajaj Aditya
 */

/**
 * The Class AssignmentController.
 */

public class AssignmentController implements Initializable {

    /** The list box main. */
    @FXML
    private ListView<String> listBoxMain;
    
    /** The asgn. */
    static String asgn;
    
    /** The assignment model. */
    private static AssignmentModel assignmentModel = new AssignmentModel();

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

    /** The assign. */
    private ObservableList<String> assign = FXCollections.observableArrayList(displayAssignments());

    /**
     * Initialize.
     *
     * @param url the url
     * @param resourceBundle the resource bundle
     */
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

    /**
     * Display assignments.
     *
     * @return the list
     */
    public List<String> displayAssignments() {
        CountDownLatch done = new CountDownLatch(1);
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

}
