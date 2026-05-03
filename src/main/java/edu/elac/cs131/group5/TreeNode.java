package edu.elac.cs131.group5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int label;
    TreeNode left;
    TreeNode right;

    TreeNode(int label) {
        this.label = label;
    }

    List<Integer> breadthFirst() {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.label);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();

        if (left != null) result.addAll(left.inOrder());
        result.add(label);
        if (right != null) result.addAll(right.inOrder());

        return result;
    }

    List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();

        return result;
    }

    List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();

        return result;
    }
}
