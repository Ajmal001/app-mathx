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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import main.MainClass;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class ViewAssignmentController implements Initializable {

    @FXML
    private Label nameTF;

    @FXML
    private VBox VBoxMain;
    @FXML
    private ListView<String> listBoxMain;

    public static AssignmentModel assignmentModel2 = new AssignmentModel();
    public static QuestionAnsModel questionAnsModel2 = new QuestionAnsModel();
    List<String> questionids;
    List<String> questions = new ArrayList<>();

    String asgn = AssignmentController.asgn;
    ObservableList<String> Qlist = FXCollections.observableArrayList(displayQuestions(asgn));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listBoxMain.setItems(Qlist);
        System.out.println("---"+Qlist);
        //displayQuestions(asgn);
        nameTF.setText(asgn);
        System.out.println(asgn);
    }
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
    void back(ActionEvent actionEvent) {
        //new MainClass().assignmentWindow();
        MainClass.view_asgnStage.close();
    }

    /**
     * Initialize.
     * <p>
     * //     * @param url            the url
     * //     * @param resourceBundle the resource bundle
     */

    List<String> displayQuestions(String Assignment) {
        CountDownLatch done = new CountDownLatch(1);
        final String[] message = {null};
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Assignment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    assignmentModel2 = data.getValue(AssignmentModel.class);
                    assignmentModel2.setId(data.getKey());
                    if (assignmentModel2.getAssignmentName().equals(asgn)) {
                        questionids = assignmentModel2.getQuestions();
                    }
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
        for (int i = 0; i < questionids.size(); i++)
            System.out.println("===" + questionids.get(i));

        CountDownLatch done2 = new CountDownLatch(1);
        final String[] message2 = {null};
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
        for (int i = 0; i < questions.size(); i++)
            System.out.println(questions.get(i));
        System.out.println("****"+questions);
        return questions;

    }


//    public static void main(String[] args) {
//
//        ViewAssignmentController vm = new ViewAssignmentController();
//        vm.displayQuestions("Assignment1");
//    }
}
