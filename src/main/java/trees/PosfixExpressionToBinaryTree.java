package trees;

import java.util.Scanner;
import java.util.Stack;
import static util.MathUtility.isOperator;

public class PosfixExpressionToBinaryTree {
    public TreeNode<String> posfixToTree(String expression) {
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

    public static void main(String[] args) {
        PosfixExpressionToBinaryTree pe2bt = new PosfixExpressionToBinaryTree();
        TreeNode<String> root = pe2bt.posfixToTree("A B C D * + * E +");
        root.postorder();
    }
}
