package main.src.controllers.WorkspaceExtras;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class Extractor {

    public static <T extends Pane> Map<Node, Object> getAllTextFields(T parent) {
        return getAllTextFields(parent, new HashMap<>());
    }


    private static <T extends Pane> Map<Node, Object> getAllTextFields(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {

            //DO NOT MODIFY THIS
            if (node instanceof TextField) {
                map.put(node, node.localToScene(node.getBoundsInLocal()));
            }

            //DO NOT MODIFY THIS
            // Recursive.
            if (node instanceof Pane) {
                getAllTextFields((Pane) node, map);
            }

        }

        return map;
    }

    public static <T extends Pane> Map<String, Double> getExpressionData(T parent) {
        return getExpressionData(parent, new HashMap<>());
    }

    private static <T extends Pane> Map<String, Double> getExpressionData(T parent, Map<String, Double> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.
            if (node instanceof TextField) {
                map.put(((TextField) node).getText(), node.localToScene(node.getBoundsInLocal()).getMinX());
            }
            if (node instanceof Label) {
                map.put(((Label) node).getText(), node.localToScene(node.getBoundsInLocal()).getMinX());
            }
            // Recursive.
            if (node instanceof Pane) {
                getExpressionData((Pane) node, map);
            }

        }
        return map;
    }

    public static Map<Node, Bounds> getAllExpressions(Pane parent) {
        HashMap hashMap = new HashMap<>();
        for (Node node : parent.getChildren()) {
            hashMap.put(node, node.localToScene(node.getBoundsInLocal()));
        }
        return hashMap;
    }


}
