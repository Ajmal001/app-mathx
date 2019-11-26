package main.src.controllers.Listeners;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.src.controllers.WorkspaceExtras.ExpressionEvaluator;

/**
 * @author Karandeep Singh Grewal
 */

public class CommonPaneListener {
    StackPane questionPane = new StackPane();
    Pane resultPane = new Pane();

    public void produceQuestionPane(StackPane questionPane, String question) {
        questionPane.setAlignment(Pos.TOP_LEFT);
        Label tempLabel = new Label("Questions");
        tempLabel.setStyle("-fx-font-size: 24;");
        String[] questionArray = question.split("<SEP>");
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10));
        for (String currentQuestion : questionArray
        ) {
            System.out.println(currentQuestion);
            String temp = currentQuestion.substring(currentQuestion.indexOf("<HINT:") + 6, currentQuestion.indexOf(":HINT>"));
            String[] prompts = temp.split(",");
            int numberOfAnswers = prompts.length;
            Label questionLabel = new Label(currentQuestion.substring(0, currentQuestion.indexOf("<HINT:")));
            questionLabel.setStyle("-fx-font-size: 20");
            questionPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#F5F5F5"), CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.getChildren().addAll(questionLabel);
            for (int tempCount = 0; tempCount < numberOfAnswers; tempCount++) {
                TextField answer = new TextField();
                answer.setMinWidth(200);
                answer.setPrefWidth(200);
                answer.setMaxWidth(200);
                answer.setPadding(new Insets(10));
                answer.setPromptText(prompts[tempCount]);
                answer.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: BOLD;-fx-background-color: #FFFFFF;-fx-border-color: #BBBBBB;-fx-border-radius: 5;" +
                        "-fx-background-radius: 5; -fx-opacity: 1;-fx-text-fill: #000000");
                vBox.getChildren().add(answer);
            }
        }
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setMaxHeight(480);
        scrollPane.setMaxWidth(1650);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        questionPane.getChildren().add(scrollPane);
    }

    private void produceResultPane(Pane resultPane) {
        Label tempLabel = new Label("Result");
        tempLabel.setStyle("-fx-font-size: 24;");
        resultPane.getChildren().add(tempLabel);
        resultPane.setBackground(new Background(new BackgroundFill(Color.valueOf("E5E5E5"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void produceCommonPane(StackPane commonPane, String question) {
        produceQuestionPane(questionPane, question);
        produceResultPane(resultPane);
        commonPane.getChildren().add(questionPane);

    }

    public void switchPane(Pane sandBox, StackPane commonPane) {
        //Switches StackPane(resultPane) to Pane(questionPane)
        if (commonPane.getChildren().toString().contains("StackPane")) {
            commonPane.getChildren().clear();
            commonPane.getChildren().addAll(resultPane);
            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
            expressionEvaluator.produceResult(sandBox, commonPane);
        } else {
            commonPane.getChildren().clear();
            commonPane.getChildren().addAll(questionPane);
        }
    }
}
