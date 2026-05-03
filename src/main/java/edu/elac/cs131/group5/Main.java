package edu.elac.cs131.group5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class Main extends Application {

    VBox root = new VBox(10);
    Button breadthFirstButton = new Button("Breadth-first search");
    Button currentlyDisabled = breadthFirstButton;
    Label orderingLabel = new Label("");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Setup UI elements
        root.setPadding(new Insets(20));

        HBox buttonsBox = new HBox(20);
        Button inOrderButton = new Button("In-order search");
        Button preOrderButton = new Button("Pre-order search");
        Button postOrderButton = new Button("Post-order search");

        buttonsBox.getChildren().addAll(breadthFirstButton, inOrderButton, preOrderButton, postOrderButton);
        root.getChildren().addAll(buttonsBox, orderingLabel);

        // Setup tree and button actions
        TreeNode treeRoot = initializeTree();

        Button disabledButton;
        breadthFirstButton.setOnAction(event -> {
            onTraversalSelected(breadthFirstButton, treeRoot.breadthFirst());
        });

        inOrderButton.setOnAction(event -> {
            onTraversalSelected(inOrderButton, treeRoot.inOrder());
        });

        preOrderButton.setOnAction(event -> {
            onTraversalSelected(preOrderButton, treeRoot.preOrder());
        });

        postOrderButton.setOnAction(event -> {
            onTraversalSelected(postOrderButton, treeRoot.postOrder());
        });

        breadthFirstButton.fire();

        // Finalize scene
        Scene scene = new Scene(root, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Binary Tree Traversal");
        primaryStage.show();
    }

    void onTraversalSelected(Button pressed, List<Integer> ordering) {
        currentlyDisabled.setDisable(false);
        pressed.setDisable(true);
        currentlyDisabled = pressed;
        orderingLabel.setText(ordering.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" -> ")));
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

}
