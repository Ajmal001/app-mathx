package main.src.controllers.Listeners;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * @author Karandeep Singh Grewal
 */

public class SidePaneListeners {

    //installs the toolTip to the sidepane operator
    //bug: doesn't perform as expected sometimes. requires testing
    public void installToolTip(StackPane stackPane) {
        Tooltip tooltip = new Tooltip("Tooltip");
        tooltip.setShowDelay(new Duration(100));
        final ObservableList<Node> sideBarOperators = stackPane.getChildren().filtered(i -> i instanceof HBox);
//        Tooltip.install(sideBarOperators.get(0), tooltip);
    }

}
