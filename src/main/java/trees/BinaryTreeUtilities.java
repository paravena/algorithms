package trees;

import java.util.Scanner;
import java.util.Stack;
import static util.MathUtility.isOperator;

public class BinaryTreeUtilities {
    // Based on http://en.wikipedia.org/wiki/Binary_expression_tree
    public static TreeNode<String> posfixToTree(String expression) {
        Stack<TreeNode<String>> stack = new Stack<TreeNode<String>>();
        Scanner scan = new Scanner(expression);
        while (scan.hasNext()) {
            String token = scan.next();
            if (isOperator(token)) {
                TreeNode<String> right = stack.pop();
                TreeNode<String> left = stack.pop();
                TreeNode<String> operator = new TreeNode<String>(token);
                operator.setLeft(left);
                operator.setRight(right);
                stack.push(operator);
            } else {
                stack.push(new TreeNode<String>(token));
            }
        }
        return stack.pop();
    }

    public static boolean isBinarySearchTree(TreeNode<Integer> treeNode) {
        return isBinarySearchTree(treeNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBinarySearchTree(TreeNode<Integer> treeNode, Integer min, Integer max) {
        return treeNode == null || treeNode.element > min &&
                treeNode.element < max &&
                isBinarySearchTree(treeNode.left, min, treeNode.element) &&
                isBinarySearchTree(treeNode.right, treeNode.element, max);
    }

    public static void main(String[] args) {
        TreeNode<String> root = BinaryTreeUtilities.posfixToTree("A B C D * + * E +");
        root.postorder();
    }
}
