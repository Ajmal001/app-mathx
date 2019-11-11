package main.src.controllers.WorkspaceExtras;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SandBoxChildren {

    public static <T extends Pane> Map<Node, Object> formValues(T parent) {
        return formValues(parent, new HashMap<>());
    }

    private static <T extends Pane> Map<Node, Object> formValues(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.
            if (node instanceof TextField) {
                map.put(node, ((TextField) node).getWidth());
            }

            // Recursive.
            if (node instanceof Pane) {
                formValues((Pane) node, map);
            }

        }

        return map;
    }
}
