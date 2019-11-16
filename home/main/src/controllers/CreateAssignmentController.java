package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CreateAssignmentController {

    @FXML
    private TextField quesTF;

    @FXML
    private TextField ansTF;

    /**
     * The grade.
     */
    @FXML
    private ComboBox grade;

    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Add Question Form Error");
        alert.setHeaderText(null);
        //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirm(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question Added");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static QuestionAnsModel questionAnsModel = new QuestionAnsModel();
    static List<QuestionAnsModel> questionAnsModelList = new ArrayList<>();

    /**
     * Use this method to push questions to the DB
     * This Pushes grade,question and ans to firebase
     */

    void pushAssignment(String assignmentName, String grade, List<String> questionId){

        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        AssignmentModel assignmentModel = new AssignmentModel();
        assignmentModel.setGrade(grade);
        assignmentModel.setAssignmentName(assignmentName);
        assignmentModel.setQuestions(questionId);

        firebase.child("Assignment").push().setValue(assignmentModel);

        System.out.println("Assignment pushed Successfully");




    }
    void pushQuestions(String grade, String question, String answer) {

        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        QuestionAnsModel questionAns = new QuestionAnsModel();

        questionAns.setGrade(grade);
        questionAns.setQuestion(question);
        questionAns.setAns(answer);

        firebase.child("Grade").push().setValue(questionAns);
        showConfirm("Question Added successfully");
        System.out.println("Question pushed Successfully");
    }
    /**
     * Use this method to display questions,answers for a particular grade
     * This returns a list of questions and answers
     */

    /**
     * Add questions dynamically action.
     *
     * @param actionEvent the action event
     */
    @FXML
    void addqs(ActionEvent actionEvent) {
        String ques = quesTF.getText();
        String ans = ansTF.getText();

//Checking if Question is not empty
        if (ques.isEmpty()) {
            showAlert("Please enter a question");
            quesTF.requestFocus();
            return;
        }
//Checking if Answer is not empty
        else if (ans.isEmpty()) {
            showAlert("Please enter the Answer to your Question ");
            ansTF.requestFocus();
            return;
        }/*
        else if (grade.getValue() == null) {
            showAlert("Please choose a Grade");
            return;
        }*/ else {
        /*
            push question to current list
            push question to question bucket on firebase
        */
            CreateAssignmentController ca = new CreateAssignmentController();
            ca.pushQuestions("1", ques, ans);
        }
    }


    List<List<String>> displayQuestions(String grade) {

        CountDownLatch done = new CountDownLatch(1);
        final String message[] = {null};

        List<List<String>> questionanslist = new ArrayList<>();
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    questionAnsModel = data.getValue(QuestionAnsModel.class);
                    questionAnsModel.setId(data.getKey());
                    questionAnsModelList.add(questionAnsModel);
                    System.out.println("Size:" + questionAnsModelList.size());
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


        for (int i = 0; i < questionAnsModelList.size(); i++) {

            QuestionAnsModel questionAns = questionAnsModelList.get(i);
            if (questionAns.getGrade().equals(grade)) {

                List<String> list = new ArrayList<>();
                list.add(questionAns.getQuestion());
                list.add(questionAns.getAns());
                questionanslist.add(list);
            }
        }
        return questionanslist;
    }


    /** Uncomment the main method to test
     *
     */
    /**
     public static void main(String[] args) {


        CreateAssignmentController createAssignmentController = new CreateAssignmentController();
        createAssignmentController.pushQuestions("8","what is 5+18","23");



     List<List<String>> result = createAssignmentController.displayQuestions("8");

     System.out.println(result.size());

     for (int i = 0; i < result.size(); i++) {

     System.out.println("Question:" + result.get(i).get(0));
     System.out.println("Answer:" + result.get(i).get(1));

     }

         List<String> questionid = new ArrayList<>();
         questionid.add("id1");
         questionid.add("id2");
         questionid.add("id3");
         createAssignmentController.pushAssignment("Assignment1","2",questionid);



    }


    **/
}
