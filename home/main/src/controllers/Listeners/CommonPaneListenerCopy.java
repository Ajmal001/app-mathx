package main.src.controllers.Listeners;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.src.controllers.WorkspaceExtras.ExpressionEvaluator;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Karandeep Singh Grewal
 */

public class CommonPaneListenerCopy {
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
            String[] prompts = new String[0];
            int promptStatus = 0;
            if (currentQuestion.contains("PROMPT")) {
                String temp = currentQuestion.substring(currentQuestion.indexOf("<PROMPT:"), currentQuestion.lastIndexOf(":PROMPT>"));
                prompts = temp.replace("<PROMPT:", "").replace(":PROMPT>", "").split(",");
                promptStatus = 1;
                currentQuestion = currentQuestion.substring(0, currentQuestion.indexOf("<PROMPT:"));
            }
            int numberOfAnswers = StringUtils.countMatches(currentQuestion, "<TEXTFIELD>");
            Label questionLabel = new Label(currentQuestion.replace("<TEXTFIELD>", ""));
            questionLabel.setStyle("-fx-font-size: 20");
            questionPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#F5F5F5"), CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.getChildren().addAll(questionLabel);
            for (int tempCount = 0; tempCount < numberOfAnswers; tempCount++) {
                TextField answer = new TextField();
                answer.setMinWidth(50);
                answer.setPrefWidth(50);
                if (promptStatus == 1) {
                    answer.setPromptText(prompts[tempCount]);
                }
                answer.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: BOLD;-fx-background-color: #FFFFFF;-fx-border-color: #BBBBBB;-fx-border-radius: 5;" +
                        "-fx-background-radius: 5; -fx-opacity: 1;-fx-text-fill: #000000");
                vBox.getChildren().add(answer);
            }

        }
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setMaxHeight(520);
        scrollPane.setMaxWidth(1670);
        questionPane.getChildren().add(scrollPane);
    }

    private void produceResultPane(Pane resultPane) {
        Label tempLabel = new Label("Result");
        tempLabel.setStyle("-fx-font-size: 24;");
        resultPane.getChildren().add(tempLabel);
        resultPane.setBackground(new Background(new BackgroundFill(Color.valueOf("E5E5E5"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void produceCommonPane(StackPane stackPane, String question) {
        produceQuestionPane(questionPane, question);
        produceResultPane(resultPane);
        stackPane.getChildren().add(questionPane);
    }

    public void switchPane(Pane sandBox, StackPane stackPane) {
        //Switches StackPane(resultPane) to Pane(questionPane)
        if (stackPane.getChildren().toString().contains("StackPane")) {
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(resultPane);
            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
            expressionEvaluator.produceResult(sandBox, stackPane);


        } else {
            stackPane.getChildren().clear();
            stackPane.getChildren().addAll(questionPane);
        }
    }
}
