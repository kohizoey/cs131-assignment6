package edu.elac.cs131.group5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TreeNode {
    int label;
    TreeNode left;
    TreeNode right;

    TreeNode(int label) {
        this.label = label;
    }

    static void labelBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int value = 1;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            current.label = value++;

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
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

        result.add(label);
        if (left != null) result.addAll(left.preOrder());
        if (right != null) result.addAll(right.preOrder());

        return result;
    }

    List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();

        if (left != null) result.addAll(left.postOrder());
        if (right != null) result.addAll(right.postOrder());
        result.add(label);

        return result;
    }

    /*
    static TreeNode buildTree() {

        TreeNode root = new TreeNode(0);

        root.left = new TreeNode(0);
        root.right = new TreeNode(0);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(0);

        root.left.left.left = new TreeNode(0);
        root.left.left.right = new TreeNode(0);
        root.left.right.left = new TreeNode(0);
        root.left.right.right = new TreeNode(0);
        root.right.left.left = new TreeNode(0);
        root.right.left.right = new TreeNode(0);
        root.right.right.left = new TreeNode(0);
        root.right.right.right = new TreeNode(0);

        return root;
    }

    public static void main(String[] args) {

        TreeNode root = buildTree();

        labelBFS(root);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. In-order");
            System.out.println("2. Pre-order");
            System.out.println("3. Post-order");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            if (choice == 1)
                System.out.println(root.inOrder());
            else if (choice == 2)
                System.out.println(root.preOrder());
            else if (choice == 3)
                System.out.println(root.postOrder());
            else
                break;
        }
        sc.close();
    }
     */
}
