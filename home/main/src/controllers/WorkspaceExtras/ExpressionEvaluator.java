package main.src.controllers.WorkspaceExtras;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

    public void produceResult(Pane sandBox, StackPane commonPane) {
        HashMap expresssions;
        expresssions = Extractor.getAllExpressions(sandBox);
        HashMap expressionData;
        if (commonPane.getChildren().toString().contains("StackPane")==false) {

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
            Expression expression = new Expression(expressionInput);
            String result = expressionInput + " = " + expression.calculate();
            Label label = new Label();
            resultPane.getChildren().addAll(label);
            label.setText(result);
            label.setStyle("-fx-font-size: 20");

            Bounds bounds = ((StackPane) node).localToScene(((StackPane) node).getLayoutBounds());
            //These numbers are adjustments done to view result parallel to the expression in the sandBox
            label.setLayoutX(bounds.getMinX()-250);
            label.setLayoutY((bounds.getMinY()-30)*0.99);



        }
        System.out.println("________________________________");

    }
}
