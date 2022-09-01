package com.company;

import java.util.LinkedList;
import java.util.Queue;

class AVLNode {
    int val;
    int height;
    AVLNode left;
    AVLNode right;

    public AVLNode(int val) {
        this.val = val;
        height = 1;

    }

    public AVLNode(int val, AVLNode left, AVLNode right) {
        this.val = val;
        height = 1;
        this.left = left;
        this.right = right;
    }
}

public class AVLTree {
    AVLNode root;

    int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    public AVLNode rightRotate(AVLNode node) {
        AVLNode temp1 = node.left;
        AVLNode temp2 = temp1.right;

        temp1.right = node;
        node.left = temp2;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp1.height = Math.max(height(temp1.left), height(temp1.right)) + 1;

        return temp1;
    }

    public AVLNode leftRotate(AVLNode node) {
        AVLNode temp1 = node.right;
        AVLNode temp2 = temp1.left;


        temp1.left = node;
        node.right = temp2;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp1.height = Math.max(height(temp1.left), height(temp1.right)) + 1;

        return temp1;
    }

    public int getBalance(AVLNode node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }

    public AVLNode insert(AVLNode node, int val) {

        if (node == null)
            return (new AVLNode(val));

        if (val < node.val)
            node.left = insert(node.left, val);
        else if (val > node.val)
            node.right = insert(node.right, val);
        else
            return node;


        node.height = 1 + Math.max(height(node.left),
                height(node.right));


        int balance = getBalance(node);

        if (balance > 1 && val < node.left.val)
            return rightRotate(node);

        if (balance < -1 && val > node.right.val)
            return leftRotate(node);

        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode minNode(AVLNode node) {
        AVLNode curr = node;

        while (curr.left != null)
            curr = curr.left;

        return curr;
    }

    AVLNode deleteNode(AVLNode root, int val) {
        if (root == null)
            return root;

        if (val < root.val)
            root.left = deleteNode(root.left, val);

        else if (val > root.val)
            root.right = deleteNode(root.right, val);

        else {

            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {


                AVLNode temp = minNode(root.right);
                root.val = temp.val;
                root.right = deleteNode(root.right, temp.val);
            }
        }


        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void preOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void BFS() {
        Queue<AVLNode> q = new LinkedList<>();
        q.add(root);
        System.out.println(root);
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                AVLNode curr = q.poll();
                System.out.print(curr.val + "\t");
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);

            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < 10; i++) {
            avlTree.root = avlTree.insert(avlTree.root, i);

        }
        avlTree.BFS();


    }


}

