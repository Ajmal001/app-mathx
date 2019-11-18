package main.src.controllers.WorkspaceExtras;

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
            System.out.println(expressionInput);
            Expression expression = new Expression(expressionInput);
            String result = expressionInput + expression.calculate();
            System.out.println(commonPane.getChildren());


        }
        System.out.println("________________________________");

    }
}
