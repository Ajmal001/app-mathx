package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.MainClass;
import main.src.controllers.Grades.GradeFive;
import main.src.controllers.Grades.GradeParent;
import main.src.controllers.Grades.GradeTwo;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;


/**
 * @author Karandeep Singh Grewal
 */


public class WorkspaceController implements Initializable {
    public static AssignmentModel assignmentModelw = new AssignmentModel();
    public static QuestionAnsModel questionAnsModelw = new QuestionAnsModel();
    List<String> questionidsw;
    List<String> questionsw = new ArrayList<>();

    @FXML
    private Pane sandBox;
    @FXML
    public StackPane commonPane;
    @FXML
    private Label homeButton;
    @FXML
    private Label submitButton;
    @FXML
    private VBox sidePane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        String assignment = "";
//
//        HashMap<String, String> hashmap = displayQuestions(assignment);
//        String questions = "";
//        int questionNumber = 1;
//        for (String s : hashmap.keySet()) {
//            questions = questions + s;
//        }
//        System.out.println(questions);

        String question = "\n1. Two wires are 12m and 16m long. The wires are to be cut into pieces of equal length. Find the maximum length of each piece.<HINT:Maximum Length :HINT> <SEP>" +
                "\n 2. If you use 4 wires of the resultant length, what will be the area of shape formed? <HINT:Shape :HINT> <SEP>" +
                "\n 3. You have a square of side 10cm. Find its circumference and area. <HINT:Area,Circumference:HINT>";


        int studentGrade = 2;
        if (LoginController.studentModel.getGrade() != null) {
            studentGrade = Integer.parseInt(LoginController.studentModel.getGrade());
        }

        GradeParent grade = null;
        switch (studentGrade) {
            case 2:
                grade = new GradeTwo();
                break;
            case 5:
                grade = new GradeFive();
                break;
            default:
                System.out.println("Unknown Grade");
                break;

        }

        grade.produceWorkspace(sandBox, sidePane, commonPane, question);

        homeButton.setOnMouseClicked(mouseEvent -> {
            new MainClass().openHomePageWindow();
            MainClass.workspaceStage.close();
        });

        submitButton.setOnMouseClicked(mouseEvent -> {
            if (commonPane.getChildren().get(0) instanceof StackPane) {
                StackPane questionPane = null;
                if (commonPane.getChildren().get(0) instanceof StackPane) {
                    questionPane = (StackPane) commonPane.getChildren().get(0);
                }
                System.out.println(questionPane.getChildren());

            } else {
                System.out.println("Switch to Result Pane");
            }
        });

    }

    HashMap<String, String> displayQuestions(String Assignment) {
        HashMap<String, String> map = new HashMap<>();
        CountDownLatch done = new CountDownLatch(1);
        final String[] message = {null};
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Assignment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    assignmentModelw = data.getValue(AssignmentModel.class);
                    assignmentModelw.setId(data.getKey());
                    if (assignmentModelw.getAssignmentName().equals(Assignment)) {
                        questionidsw = assignmentModelw.getQuestionId();
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

        CountDownLatch done2 = new CountDownLatch(1);
        final String[] message2 = {null};
        Firebase firebase2 = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase2.child("Grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    questionAnsModelw = data.getValue(QuestionAnsModel.class);
                    questionAnsModelw.setId(data.getKey());
                    if (questionidsw.contains(questionAnsModelw.getId())) {
                        questionsw.add(questionAnsModelw.getQuestion());
                        map.put(questionAnsModelw.getQuestion(), questionAnsModelw.getAns());
                    }
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
        return map;

    }

}