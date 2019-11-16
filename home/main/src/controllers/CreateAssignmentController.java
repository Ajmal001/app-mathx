package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CreateAssignmentController {
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

        System.out.println("Question pushed Successfully");
    }

    /**
     * Use this method to display questions,answers for a particular grade
     * This returns a list of questions and answers
     */

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
