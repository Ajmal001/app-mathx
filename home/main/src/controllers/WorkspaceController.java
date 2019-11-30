package main.src.controllers;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MainClass;
import main.src.controllers.Grades.GradeFive;
import main.src.controllers.Grades.GradeParent;
import main.src.controllers.Grades.GradeTwo;
import main.src.controllers.WorkspaceExtras.Extractor;
import main.src.models.AssignmentModel;
import main.src.models.QuestionAnsModel;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;


/**
 * @author Karandeep Singh Grewal
 */


@SuppressWarnings("unchecked")
public class WorkspaceController implements Initializable {
    private static AssignmentModel assignmentModelw = new AssignmentModel();
    private static QuestionAnsModel questionAnsModelw = new QuestionAnsModel();
    private List<String> questionidsw;
    private List<String> questionsw = new ArrayList<>();

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
    @FXML
    private Label assignmentName;

    //Sorts the hashmap by its keys
    private static String sortbykey(HashMap map) {
        ArrayList<Double> sortedKeys = new ArrayList<Double>(map.keySet());
        Collections.sort(sortedKeys);
        StringBuilder expressionInput = new StringBuilder();
        for (Double x : sortedKeys) {
            expressionInput.append(map.get(x));
            expressionInput.append(" ,");
        }
        return expressionInput.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String assignment = HomepageController.selectedAssignment;
        assignmentName.setText("Assignment: " + assignment);
        //get the questions from the database
        HashMap<String, String> hashmap = displayQuestions(assignment);
        StringBuilder question = new StringBuilder();
        StringBuilder answers = new StringBuilder();
        int questionNumber = 1;
        for (String string : hashmap.keySet()) {
            question.append("\n").append(questionNumber++).append(". ").append(string).append("<SEP>");
            answers.append(hashmap.get(string).replaceAll(" ", "")).append(" ,");
        }

        int studentGrade = 2;
        if (LoginController.studentModel.getGrade() != null) {
            studentGrade = Integer.parseInt(LoginController.studentModel.getGrade());
        }

        //create the grade object
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

        //start creating the workspace
        assert grade != null;
        grade.produceWorkspace(sandBox, sidePane, commonPane, question.toString());

        homeButton.setOnMouseClicked(mouseEvent -> {
            new MainClass().openHomePageWindow();
            MainClass.workspaceStage.close();
        });

        //Check answers and give points
        String finalAnswers = answers.toString();
        submitButton.setOnMouseClicked(mouseEvent -> {
            if (commonPane.getChildren().get(0) instanceof StackPane) {
                StackPane questionPane;
                questionPane = (StackPane) commonPane.getChildren().get(0);
                Map map = (Extractor.getAllInputs((VBox) (((ScrollPane) questionPane.getChildren().get(0)).getContent())));
                String[] answerSet = sortbykey((HashMap) map).split(",");
                Alert submitConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to submit now?");
                submitConfirmation.setHeaderText("Submit Assignment");
                submitConfirmation.initStyle(StageStyle.UNDECORATED);
                submitConfirmation.getDialogPane().setPrefSize(480, 200);
                if (submitConfirmation.showAndWait().toString().contains("OK")) {
                    StringBuilder userAnswers = new StringBuilder();
                    for (Object node : answerSet
                    ) {
                        userAnswers.append(((String) node).replaceAll(" ", "")).append(" ,");
                    }
                    String[] userAnswersArray = userAnswers.toString().split(",");
                    String[] finalAnswersArray = finalAnswers.split(",");
                    int totalPoints = finalAnswersArray.length;
                    int unattempted = 0;
                    int userPoints = 0;
                    if (userAnswersArray.length == finalAnswersArray.length) {
                        for (int index = 0; index < userAnswersArray.length; index++) {
                            if (userAnswersArray[index].equals(finalAnswersArray[index])) {
                                userPoints += 1;
                            }
                            if (userAnswersArray[index].equals(" ")) {
                                unattempted += 1;
                            }
                        }
                        Pane root = new Pane();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setTitle("Result");
                        stage.setWidth(500);
                        stage.setHeight(500);
                        ObservableList<PieChart.Data> pieChartData =
                                FXCollections.observableArrayList(
                                        new PieChart.Data("Wrong", totalPoints - unattempted - userPoints),
                                        new PieChart.Data("Unattempted", unattempted),
                                        new PieChart.Data("Correct", userPoints));
                        final PieChart chart = new PieChart(pieChartData);
                        chart.setTitle("You got " + userPoints + " out of " + totalPoints);
                        chart.setStartAngle(0);
                        final Label caption = new Label("");
                        for (final PieChart.Data data : chart.getData()) {
                            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                                    e -> {
                                        double total = 0;
                                        for (PieChart.Data d : chart.getData()) {
                                            total += d.getPieValue();
                                        }
                                        caption.setTranslateX(e.getSceneX() + 5);
                                        caption.setTranslateY(e.getSceneY() + 5);
                                        caption.setTextFill(Color.valueOf("FFFFFF"));
                                        String text = String.format("%d", ((int) data.getPieValue()));
                                        caption.setText(text);
                                    }
                            );
                        }
                        root.getChildren().addAll(chart, caption);
                        stage.setScene(scene);
                        stage.show();
                        MainClass.workspaceStage.close();
                        stage.setOnCloseRequest(windowEvent -> new MainClass().openHomePageWindow());

                    }
                }
            } else {
                Alert switchPaneAlert = new Alert(Alert.AlertType.INFORMATION, "Please switch to question section to submit the assignment.");
                switchPaneAlert.setHeaderText("Unable to submit");
                switchPaneAlert.getDialogPane().setPrefSize(480, 200);
                switchPaneAlert.initStyle(StageStyle.UNDECORATED);
                switchPaneAlert.show();
            }


        });

    }

    //fetches the questions from the database
    private HashMap<String, String> displayQuestions(String Assignment) {
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