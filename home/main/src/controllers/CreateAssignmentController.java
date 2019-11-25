package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.MainClass;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CreateAssignmentController implements Initializable {

    @FXML
    private ComboBox grade;
    @FXML
    private TextField nameTF;

    @FXML
    private TextField quesTF;
    @FXML
    private TextField ansTF;

    @FXML
    private VBox VBoxMain;
    @FXML
    private ListView<String> listBoxMain;

    @FXML
    private VBox VBoxMain2;
    @FXML
    private ListView<String> listBoxQ;

    public static QuestionAnsModel questionAnsModel = new QuestionAnsModel();
    static List<QuestionAnsModel> questionAnsModelList = new ArrayList<>();

    ObservableList<String> initial = FXCollections.observableArrayList("Choose your Grade ");
    ObservableList<String> asgn = FXCollections.observableArrayList();

    ObservableList<String> one = FXCollections.observableArrayList(displayQuestions("1"));
    ObservableList<String> two = FXCollections.observableArrayList(displayQuestions("2"));
    ObservableList<String> five = FXCollections.observableList(displayQuestions("5"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   listBoxMain.setItems(initial);
        listBoxMain.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println("place 1 " + s + "--" + t1);
                if (t1 != null) {
                    asgn.add(t1);

                }
                System.out.println(asgn);
                listBoxQ.setItems(asgn);
                //AssignmentController.asgn = t1;
                // new MainClass().view_assignmentWindow();
            }
        });
        listBoxQ.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                // asgn.remove(t1);
                // listBoxQ.setItems(asgn);
                //AssignmentController.asgn = t1;
                // new MainClass().view_assignmentWindow();
            }
        });
    }

    public void setQ(ActionEvent actionEvent) {
        listBoxQ.getItems().clear();
        asgn.removeAll();
        String gr = grade.getValue().toString();
        System.out.println("=======" + gr);
        if (gr.equals("2")) {
            listBoxMain.setItems(two);
        } else if (gr.equals("5")) {
            listBoxMain.setItems(five);
        } else if (gr.equals("1")) {
            listBoxMain.setItems(one);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Add Question Error");
        alert.setHeaderText(null);
        //   alert.setHeaderText("Required Fields Empty");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showConfirm(String message,String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Use this method to push questions to the DB
     * This Pushes grade,question and ans to firebase
     */
    void pushQuestions(String grade, String question, String answer) {
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        QuestionAnsModel questionAns = new QuestionAnsModel();
        questionAns.setGrade(grade);
        questionAns.setQuestion(question);
        questionAns.setAns(answer);
        firebase.child("Grade").push().setValue(questionAns);
        showConfirm("Question Added successfully", "Question Added");
        System.out.println("Question pushed Successfully");
    }

    @FXML
    void addqs(ActionEvent actionEvent) {
        String ques = quesTF.getText();
        String ans = ansTF.getText();
        String gr = grade.getValue().toString();
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
        } else if (grade.getValue() == null) {
            showAlert("Please choose a Grade");
            return;
        } else {
        /*
            push question to current list
            push question to question bucket on firebase
        */
            asgn.add(ques);
            System.out.println(asgn);
            listBoxQ.setItems(asgn);

            CreateAssignmentController ca = new CreateAssignmentController();
            ca.pushQuestions(gr, ques, ans);
        }
    }


    List<String> displayQuestions(String grade) {
        CountDownLatch done = new CountDownLatch(1);
        final String message[] = {null};
        List<String> questionanslist = new ArrayList<>();
        List<String> Qlist = new ArrayList<>();
        Set<String> set = new HashSet<String>();
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    questionAnsModel = data.getValue(QuestionAnsModel.class);
                    questionAnsModel.setId(data.getKey());
                    questionAnsModelList.add(questionAnsModel);
                    //    System.out.println("Size:" + questionAnsModelList.size());
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
                set.add(questionAns.getQuestion());
            }
        }
        Qlist.addAll(set);

        // System.out.println(Qlist);
        return Qlist;
    }

    HashMap<String,String> mapping(String grade) {
        CountDownLatch done = new CountDownLatch(1);

        List<String> Qlist = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    questionAnsModel = data.getValue(QuestionAnsModel.class);
                    questionAnsModel.setId(data.getKey());
                    questionAnsModelList.add(questionAnsModel);
                    //    System.out.println("Size:" + questionAnsModelList.size());
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
                map.put(questionAns.getQuestion(),questionAns.getId());
            }

        }

        for(String s: map.keySet())
            System.out.println("key:"+s+"value:"+map.get(s));

        return map;
    }
    List<String> displayQuestionsId(String grade) {
        CountDownLatch done = new CountDownLatch(1);
        final String message[] = {null};
        List<String> questionanslist = new ArrayList<>();
        List<String> Qlist = new ArrayList<>();
        Set<String> set = new HashSet<String>();
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        firebase.child("Grade").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    questionAnsModel = data.getValue(QuestionAnsModel.class);
                    questionAnsModel.setId(data.getKey());
                    questionAnsModelList.add(questionAnsModel);
                    //    System.out.println("Size:" + questionAnsModelList.size());
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
                set.add(questionAns.getId());
            }
        }
        Qlist.addAll(set);
        // System.out.println(Qlist);
        return Qlist;
    }

    public void createA(ActionEvent actionEvent) {
        String name = nameTF.getText();
        if (name.isEmpty()) {
            showAlert("Please enter your name");
            nameTF.requestFocus();
            return;
        } else if (asgn.isEmpty()) {
            showAlert("Please add some questions");
            return;
        } else {
            List<String> questionid = new ArrayList<>();

            HashMap<String,String> hashMap = mapping(grade.getValue().toString());
            for(String s: hashMap.keySet()){
                System.out.println("key:"+s+"value:"+hashMap.get(s));
                if(asgn.contains(s))
                    questionid.add(hashMap.get(s));
            }


            pushAssignment(name, grade.getValue().toString(),questionid);

            new MainClass().assignmentWindow();
            MainClass.create_asgnStage.close();

            showConfirm("Assignment Created","Assignment Created");

        }

    }


    void pushAssignment(String assignmentName, String grade, List<String> questionId) {
        Firebase firebase = new Firebase("https://ser515-team4.firebaseio.com/");
        AssignmentModel assignmentModel = new AssignmentModel();
        assignmentModel.setGrade(grade);
        assignmentModel.setAssignmentName(assignmentName);
        assignmentModel.setQuestionId(questionId);
        firebase.child("Assignment").push().setValue(assignmentModel);
        System.out.println("Assignment pushed Successfully");
    }




 /*
    public static void main(String[] args) {
        CreateAssignmentController cm = new CreateAssignmentController();
        System.out.println("Displaying questions");
        System.out.println(cm.displayQuestions("1"));

        System.out.println("Displaying questionsID");
        System.out.println(cm.displayQuestionsId("1"));

        System.out.println("Displaying hashmap");
        System.out.println(cm.mapping("1"));
    }
*/

}
