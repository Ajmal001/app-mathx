package main.src.controllers.WorkspaceExtras;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class ResultExtractor {

    public static void getResultLayouts(Pane parent) {
        for (Node node : parent.getChildren()) {
//            System.out.println(node.localToScene(node.getBoundsInLocal()).getMinX());
            System.out.println(node);
        }
    }

    public static <T extends StackPane> Map<Node, Object> formValues(T parent) {
        return formValues(parent, new HashMap<>());
    }

    private static <T extends StackPane> Map<Node, Object> formValues(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.

            if (node instanceof Label) {
                System.out.println(((Label) node).getText());
            }

            if (node instanceof TextField) {
                System.out.println(((TextField) node).getText());
            }

            // Recursive.
            if (node instanceof StackPane) {
                formValues((StackPane) node, map);
            }

        }

        return map;
    }

}
