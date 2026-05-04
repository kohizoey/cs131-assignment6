package edu.elac.cs131.group5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {

    private final VBox root = new VBox(10);
    private final Label orderingLabel = new Label("");
    private Button currentlyDisabled = null;

    @Override
    public void start(Stage primaryStage) {
        // Setup UI elements
        root.setPadding(new Insets(20));

        HBox buttonsBox = new HBox(20);
        Button inOrderButton = new Button("In-order search");
        Button preOrderButton = new Button("Pre-order search");
        Button postOrderButton = new Button("Post-order search");

        buttonsBox.getChildren().addAll(inOrderButton, preOrderButton, postOrderButton);

        // Setup tree and button actions
        TreeNode treeRoot = initializeTree();

        inOrderButton.setOnAction(event -> {
            onTraversalSelected(inOrderButton, treeRoot.inOrder());
        });

        preOrderButton.setOnAction(event -> {
            onTraversalSelected(preOrderButton, treeRoot.preOrder());
        });

        postOrderButton.setOnAction(event -> {
            onTraversalSelected(postOrderButton, treeRoot.postOrder());
        });

        // Draw BFS tree
        Pane treePane = new Pane();
        drawTree(treePane, treeRoot, 400, 40, 200);

        // Finalize scene
        root.getChildren().addAll(buttonsBox, orderingLabel, treePane);
        Scene scene = new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Binary Tree Traversal");
        primaryStage.show();
    }

    void onTraversalSelected(Button pressed, List<Integer> ordering) {
        if (currentlyDisabled != null) {
            currentlyDisabled.setDisable(false);
        }
        pressed.setDisable(true);
        currentlyDisabled = pressed;
        orderingLabel.setText(ordering.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" → ")));
        root.requestFocus();
    }

    TreeNode initializeTree() {
        int totalNodes = 15;
        TreeNode[] nodes = new TreeNode[totalNodes + 1];

        for (int i = 1; i <= totalNodes; i++) {
            nodes[i] = new TreeNode(i);
        }

        for (int i = 1; i <= totalNodes / 2; i++) {
            nodes[i].left = nodes[2 * i];
            nodes[i].right = nodes[2 * i + 1];
        }

        return nodes[1];
    }

    void drawTree(Pane pane, TreeNode node, double x, double y, double spread) {
        double radius = 20;

        if (node.left != null) {
            double childX = x - spread;
            double childY = y + 80;
            pane.getChildren().add(new Line(x, y, childX, childY));
            drawTree(pane, node.left, childX, childY, spread / 2);
        }

        if (node.right != null) {
            double childX = x + spread;
            double childY = y + 80;
            pane.getChildren().add(new Line(x, y, childX, childY));
            drawTree(pane, node.right, childX, childY, spread / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Label label = new Label(String.valueOf(node.label));
        label.setLayoutX(x - 6);
        label.setLayoutY(y - 10);

        pane.getChildren().addAll(circle, label);
    }

}
