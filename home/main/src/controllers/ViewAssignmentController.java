package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import main.MainClass;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;
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
 * The Class ViewAssignmentController.
 */


public class ViewAssignmentController implements Initializable {

    /** The name TF. */
    @FXML
    private Label nameTF;
    
    /** The list box main. */
    @FXML
    private ListView<String> listBoxMain;

    /** The assignment model 2. */
    private static AssignmentModel assignmentModel2 = new AssignmentModel();
    
    /** The question ans model 2. */
    private static QuestionAnsModel questionAnsModel2 = new QuestionAnsModel();
    
    /** The questionids. */
    private List<String> questionids;
    
    /** The questions. */
    private List<String> questions = new ArrayList<>();
    
    /** The asgn. */
    private String asgn = AssignmentController.asgn;
    
    /** The Qlist. */
    private ObservableList<String> Qlist = FXCollections.observableArrayList(displayQuestions(asgn));

    /**
     * Initialize.
     *
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listBoxMain.setItems(Qlist);
        System.out.println("---"+Qlist);
        //displayQuestions(asgn);
        nameTF.setText(asgn);
        System.out.println(asgn);
    }

    /**
     * Create assignment action.
     *
     * @param actionEvent the action event
     */
    @FXML
    void back(ActionEvent actionEvent) {
        MainClass.view_asgnStage.close();
    }

    /**
     * Initialize.
     * <p>
     * //     * @param url            the url
     * //     * @param resourceBundle the resource bundle
     *
     * @param Assignment the assignment
     * @return the list
     */

    List<String> displayQuestions(String Assignment) {
        CountDownLatch done = new CountDownLatch(1);
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Assignment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    assignmentModel2 = data.getValue(AssignmentModel.class);
                    assignmentModel2.setId(data.getKey());
                    if (assignmentModel2.getAssignmentName().equals(asgn)) {
                        questionids = assignmentModel2.getQuestionId();
                    }
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
        for (int i = 0; i < questionids.size(); i++)
            System.out.println("===" + questionids.get(i));

        CountDownLatch done2 = new CountDownLatch(1);
        Firebase firebase2 = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase2.child("Grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    questionAnsModel2 = data.getValue(QuestionAnsModel.class);
                    questionAnsModel2.setId(data.getKey());
                    if (questionids.contains(questionAnsModel2.getId())) {
                        questions.add(questionAnsModel2.getQuestion());
                    }
                    //System.out.println("Size:" + assignmentlist.size());
                }
                done2.countDown();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        try {
            done2.await(); //it will wait till the response is received from firebase.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return questions;

    }

}
