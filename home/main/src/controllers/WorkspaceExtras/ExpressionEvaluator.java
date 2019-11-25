package main.src.controllers.WorkspaceExtras;

import javafx.animation.PauseTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Karandeep Singh Grewal
 */

public class ExpressionEvaluator {
    public Pane resultPane;

    private static String sortbykey(HashMap map) {
        ArrayList<Double> sortedKeys = new ArrayList<Double>(map.keySet());
        Collections.sort(sortedKeys);
        StringBuilder expressionInput = new StringBuilder();
        for (Double x : sortedKeys) {
            expressionInput.append(map.get(x));
        }
        return expressionInput.toString();
    }

    private boolean isNumber(String string) {
        boolean answer = false;
        String regex = "[+-]?[0-9][0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find() && matcher.group().equals(string))
            answer = true;

        return answer;
    }

    private void showAlert(String answer) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submission");
        alert.setHeaderText(null);
        alert.setContentText(answer);
        alert.showAndWait();
    }


    public void produceResult(Pane sandBox, StackPane commonPane) {
        HashMap expresssions;
        expresssions = Extractor.getAllExpressions(sandBox);
        HashMap expressionData;
        if (commonPane.getChildren().toString().contains("StackPane") == false) {

            resultPane = (Pane) commonPane.getChildren().get(0);
            resultPane.getChildren().clear();
        }

        for (Object node : expresssions.keySet()) {
            expressionData = (HashMap) Extractor.getExpressionData((StackPane) node);
            HashMap<Double, String> rawData = new HashMap<>();
            for (Object value : expressionData.entrySet()) {
                String[] temp = value.toString().split("=");
                Double coordinate = Double.valueOf(temp[0]);
                StringBuilder expression = new StringBuilder();
                for (int something = 1; something < temp.length; something++) {
                    expression.append(temp[something]);
                }
                rawData.put(coordinate, expression.toString());
            }
            String expressionInput = sortbykey(rawData);
            if (expressionInput.contains("Equation:")) {
                expressionInput.replace("Equation:", "");
            }
            Expression expression = new Expression(expressionInput);
//            System.out.println(expressionInput);
            double result = (Math.round(expression.calculate() * 1000000));
            result = result / 1000000;
            double finalResult;
            if (result % 1 == 0) {
                int integerResult = (int) result;
                finalResult = integerResult;
            } else {
                finalResult = result;
            }
            Label label = new Label();
            resultPane.getChildren().addAll(label);
            label.setText((finalResult + " ").replace(".0 ", " "));
            if (expressionInput.contains(">") || expressionInput.contains("<"))
                if (finalResult == 1)
                    label.setText("True");
                else
                    label.setText("False");
            label.setStyle("-fx-font-size: 30");
            HashMap answers = (HashMap) Extractor.getAllAnswers(resultPane);
            int counter = 0;
            for (Object str : answers.entrySet()) {
                if (str.toString().contains("True"))
                    counter++;
            }
            if (counter > 2) {
                showAlert("Successful");
            }

            if (expressionInput.contains("4^2") || expressionInput.contains("4*4")) {
                showAlert("Successful");
            }

            Bounds bounds = ((StackPane) node).localToScene(((StackPane) node).getLayoutBounds());
            //These numbers are adjustments done to view result parallel to the expression in the sandBox
            label.setLayoutX(bounds.getMinX() - 270);
            label.setPrefWidth(bounds.getWidth());
            label.setAlignment(Pos.CENTER);
            label.setLayoutY((bounds.getMinY() - 40) * 0.95);
            label.setStyle("-fx-border-color: black; -fx-label-padding: 10; -fx-border-radius: 5; -fx-border-width: 2");
            label.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    content.putString(label.getText());
                    PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                    Tooltip tooltip = new Tooltip("Result Copied: " + label.getText());
                    tooltip.show(label, mouseEvent.getSceneX(), mouseEvent.getSceneY());
                    delay.setOnFinished(e -> tooltip.hide());
                    delay.play();
                    clipboard.setContent(content);
                }
            });
        }
    }
}
