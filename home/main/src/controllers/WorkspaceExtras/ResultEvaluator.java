package main.src.controllers.WorkspaceExtras;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ResultEvaluator {

    private static String sortbykey(HashMap map) {
        ArrayList<Double> sortedKeys = new ArrayList<Double>(map.keySet());
        Collections.sort(sortedKeys);
        StringBuilder expressionInput = new StringBuilder();
        for (Double x : sortedKeys) {
            expressionInput.append(map.get(x));
        }
        return expressionInput.toString();
    }

    public void produceResultInput(Pane sandBox) {
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
            System.out.println(((StackPane) node).localToParent(((StackPane) node).getLayoutBounds()));
            System.out.println(expressionInput);

        }
        System.out.println("________________________________");

    }
}
