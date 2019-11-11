package main.src.controllers.WorkspaceExtras;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class AllRectangles {

    public static <T extends Pane> Map<Node, Object> formValues(T parent) {
        return formValues(parent, new HashMap<>());
    }

    private static <T extends Pane> Map<Node, Object> formValues(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.
            if (node instanceof Rectangle) {
                map.put(node, ((Rectangle) node).getWidth());
            }

            // Recursive.
            if (node instanceof Pane) {
                formValues((Pane) node, map);
            }

        }

        return map;
    }
}
