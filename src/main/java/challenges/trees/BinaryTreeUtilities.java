package challenges.trees;

import java.util.Scanner;
import java.util.Stack;
import static util.MathUtility.isOperator;

public class BinaryTreeUtilities {
    // Based on http://en.wikipedia.org/wiki/Binary_expression_tree
    public static TreeNode<String, String> posfixToTree(String expression) {
        Stack<TreeNode<String, String>> stack = new Stack<TreeNode<String, String>>();
        Scanner scan = new Scanner(expression);
        while (scan.hasNext()) {
            String token = scan.next();
            if (isOperator(token)) {
                TreeNode<String, String> right = stack.pop();
                TreeNode<String, String> left = stack.pop();
                TreeNode<String, String> operator = new TreeNode<String, String>(token);
                operator.setLeft(left);
                operator.setRight(right);
                stack.push(operator);
            } else {
                stack.push(new TreeNode<String, String>(token));
            }
        }
        return stack.pop();
    }

    public static boolean isBinarySearchTree(TreeNode<Integer, Integer> treeNode) {
        return isBinarySearchTree(treeNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBinarySearchTree(TreeNode<Integer, Integer> treeNode, Integer min, Integer max) {
        return treeNode == null || treeNode.key > min &&
                treeNode.key < max &&
                isBinarySearchTree(treeNode.left, min, treeNode.key) &&
                isBinarySearchTree(treeNode.right, treeNode.key, max);
    }

    public static void main(String[] args) {
        TreeNode<String, String> root = BinaryTreeUtilities.posfixToTree("A B C D * + * E +");
        root.postOrder();
    }
}
