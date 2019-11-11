package main.src.controllers.WorkspaceExtras;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class OperatorChildren {

    public static <T extends StackPane> Map<Node, Object> formValues(T parent) {
        return formValues(parent, new HashMap<>());
    }

    private static <T extends StackPane> Map<Node, Object> formValues(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.
            if (node instanceof TextField) {
                map.put(node, ((TextField) node).getWidth());
            }

            // Recursive.
            if (node instanceof StackPane) {
                formValues((StackPane) node, map);
            }

        }

        return map;
    }
}
