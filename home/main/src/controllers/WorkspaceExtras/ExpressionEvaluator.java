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

@SuppressWarnings({"ResultOfMethodCallIgnored", "unchecked"})
public class ExpressionEvaluator {
    private Pane resultPane;

    //sorts a hashmap by its keys
    private static String sortbykey(HashMap map) {
        ArrayList<Double> sortedKeys = new ArrayList<Double>(map.keySet());
        Collections.sort(sortedKeys);
        StringBuilder expressionInput = new StringBuilder();
        for (Double x : sortedKeys) {
            expressionInput.append(map.get(x));
        }
        return expressionInput.toString();
    }

    //checks if the input is a number or not
    private boolean isNumber(String string) {
        boolean answer = false;
        String regex = "[+-]?[0-9][0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find() && matcher.group().equals(string))
            answer = true;

        return answer;
    }

    //show alert box
    private void showAlert(String answer) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Submission");
        alert.setHeaderText(null);
        alert.setContentText(answer);
        alert.showAndWait();
    }

    //takes all the equation and operator data from the sandbox and computes the result and put it into result pane
    //as labels
    public void produceResult(Pane sandBox, StackPane commonPane) {
        HashMap expresssions;
        //get all the required data from the sandbox
        expresssions = Extractor.getAllExpressions(sandBox);
        HashMap expressionData;
        if (!commonPane.getChildren().toString().contains("StackPane")) {
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
            //custom filters for the data taken from the sandbox
            String expressionInput = sortbykey(rawData);
            if (expressionInput.contains("Equation:")) {
                expressionInput.replace("Equation:", "");
            }
            //calculates the result
            Expression expression = new Expression(expressionInput);
            double result = (Math.round(expression.calculate() * 1000000));
            result = result / 1000000;
            double finalResult;
            if (result % 1 == 0) {
                finalResult = (int) result;
            } else {
                finalResult = result;
            }
            //creates the label that will contain the result
            Label label = new Label();
            try {
                resultPane.getChildren().addAll(label);
            } catch (Exception e) {
                e.printStackTrace();
            }
            label.setText((finalResult + " ").replace(".0 ", " "));
            if (expressionInput.contains(">") || expressionInput.contains("<"))
                if (finalResult == 1)
                    label.setText("True");
                else
                    label.setText("False");
            label.setStyle("-fx-font-size: 30");
            Bounds bounds = ((StackPane) node).localToScene(((StackPane) node).getLayoutBounds());
            //label created above is placed into the result section
            //These numbers are adjustments done to view result parallel to the expression in the sandBox
            label.setLayoutX(bounds.getMinX() - 270);
            label.setPrefWidth(bounds.getWidth());
            label.setAlignment(Pos.CENTER);
            label.setLayoutY((bounds.getMinY() - 40) * 0.95);
            label.setStyle("-fx-border-color: black; -fx-label-padding: 10; -fx-border-radius: 5; -fx-border-width: 2");
            //listener to copy the result on double click on result label
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
