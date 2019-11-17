package main.src.controllers.WorkspaceExtras;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karandeep Singh Grewal
 */


public class NodeUtils {

    public static <T extends Pane> Map<Node, Object> formValues(T parent) {
        return formValues(parent, new HashMap<>());
    }

    private static <T extends Pane> Map<Node, Object> formValues(T parent, Map<Node, Object> map) {
        for (Node node : parent.getChildren()) {
            // Nodes - You can add more.
            if (node instanceof TextField) {
                map.put(node, ((TextField) node).getWidth());
            }
            if (node instanceof PasswordField) {
                map.put(node, ((PasswordField) node).getWidth());
            }
            if (node instanceof TextArea) {
                map.put(node, ((TextArea) node).getWidth());
            }
            if (node instanceof CheckBox) {
                map.put(node, ((CheckBox) node).getWidth());
            }
            if (node instanceof Label) {
                map.put(node, ((Label) node).getWidth());
            }

            // Recursive.
            if (node instanceof Pane) {
                formValues((Pane) node, map);
            }

        }

        return map;
    }
}
