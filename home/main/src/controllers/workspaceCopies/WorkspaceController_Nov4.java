//package main.src.controllers;
//
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Cursor;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//
//import java.net.URL;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ResourceBundle;
//
//
//public class WorkspaceController_Nov4 implements Initializable {
//
//    @FXML
//    private Pane sandBox;
//    @FXML
//    private Label homeButton;
//    @FXML
//    private VBox expressionPane;
//
//    public enum expressionType {
//        SINGLE,
//        DOUBLE,
//        TRIPLE
//    }
//
//    final double[] deltaX = new double[1];
//    final double[] deltaY = new double[1];
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        expressionPane.setSpacing(5);
//
//        Operator operator = OperatorFactory.produceExpressionType(expressionType.SINGLE);
//        operator.produceExpressionType(expressionPane);
//
//
//        Operator operator1 = OperatorFactory.produceExpressionType(expressionType.DOUBLE);
//        operator1.produceExpressionType(expressionPane);
//
//        final ObservableList<Node> sideBarOperators = expressionPane.getChildren().filtered(i -> i instanceof StackPane);
//        for (Node child : sideBarOperators) {
//            child.setOnMouseClicked(mouseEvent -> {
//                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
//                    if (mouseEvent.getClickCount() > 1) {
//                        StackPane stackPane = new StackPane();
//                        stackPane = (StackPane) child;
//                        HBox hBox = new HBox();
//                        hBox = (HBox) stackPane.getChildren().filtered(i -> i instanceof HBox).get(0);
//                        String operatorString = hBox.getChildren().filtered(i -> i instanceof Label).toString().split("'")[1];
//                        System.out.println(operatorString);
//                        String binaryOperators[] = {"+", "-", "*", "/"};
//                        List<String> binaryOperatorsList = Arrays.asList(binaryOperators);
//                        Operator newOperator;
//                        if (binaryOperatorsList.contains(operatorString)) {
//                            newOperator = OperatorFactory.produceExpressionType(expressionType.DOUBLE);
//                        } else {
//                            newOperator = OperatorFactory.produceExpressionType(expressionType.SINGLE);
//                        }
//                        StackPane stackPane1 = new StackPane();
//                        stackPane1 = newOperator.produceShape(operatorString);
//                        sandBox.getChildren().addAll(stackPane1);
//
//                        StackPane finalStackPane = stackPane1;
//                        stackPane1.setOnMouseEntered(mouseEvent1 -> finalStackPane.setCursor(Cursor.MOVE));
//                        StackPane finalStackPane1 = stackPane1;
//                        stackPane1.setOnMouseMoved(mouseEvent1 -> {
//                            deltaX[0] = finalStackPane1.getLayoutX() - mouseEvent1.getSceneX();
//                            deltaY[0] = finalStackPane1.getLayoutY() - mouseEvent1.getSceneY();
//                        });
//                        StackPane finalStackPane2 = stackPane1;
//                        stackPane1.setOnMouseDragged(mouseEvent1 -> {
//                            int rightBound = 1170;
//                            //Left X-Bound
//                            if (mouseEvent1.getSceneX() < -deltaX[0]) {
//                                finalStackPane2.setLayoutX(1);
//                            }
//                            //Right X-Bound
//                            else if (mouseEvent1.getSceneX() + finalStackPane2.getWidth() + deltaX[0] > rightBound) {
//                                finalStackPane2.setLayoutX(rightBound - finalStackPane2.getWidth());
//                            } else {
//                                finalStackPane2.setLayoutX(mouseEvent1.getSceneX() + deltaX[0]);
//                            }
//                            int lowerBound = 720;
//                            //Upper Y-Bound
//                            if (mouseEvent1.getSceneY() < -deltaY[0]) {
//                                finalStackPane2.setLayoutY(1);
//                            }
//                            //Lower Y-Bound
//                            else if (mouseEvent1.getSceneY() + finalStackPane2.getHeight() + deltaY[0] > lowerBound) {
//                                finalStackPane2.setLayoutY(lowerBound - finalStackPane2.getHeight());
//                            } else {
//                                finalStackPane2.setLayoutY(mouseEvent1.getSceneY() + deltaY[0]);  /*190,110*/ /*232,220*/
//                            }
//                        });
//
//                    }
//                }
//            });
//        }
//
//
//    }
//}