package challenges.trees;

public class IsABinarySearchTree {
    static boolean checkBST(Node root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean checkBST(Node n, int min, int max) {
        if (n == null) return true;
        if (n.data <= min || n.data >= max) return false;
        return checkBST(n.left, min, n.data) && checkBST(n.right, n.data, max);
    }
}

class Node {
    int data;
    Node left;
    Node right;
}