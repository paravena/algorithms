package challenges.trees;

import java.util.Stack;

public class TreeExpressionResolver implements TreeNodeVisitor<String> {
    private Stack<Double> numbers = new Stack<Double>();
    private Double result;

    @Override
    public void visit(String element) {
        System.out.println("visiting " + element);
        if ("+".equals(element) || "-".equals(element) || "*".equals(element) || "/".equals(element)) {

            Double operand1 = numbers.pop();
            Double operand2 = numbers.pop();
            if ("+".equals(element)) {
                System.out.println("adding " + operand1 + " and " + operand2);
                result = operand1 + operand2;
            } else if ("-".equals(element)) {
                result = operand1 - operand2;
            } else if ("*".equals(element)) {
                result = operand1 * operand2;
            } else if ("/".equals(element)) {
                System.out.println("div " + operand1 + " and " + operand2);
                result = operand2 / operand1;
            }
            numbers.push(result);
        } else {
            numbers.push(Double.parseDouble(element));
        }
    }

    public Double getResult() {
        return numbers.pop();
    }

    public static void main(String[] args) {
        /*
          This tree represents a mathematical operation : (12 + 24) / 3

               /
             /  \
           +    3
          / \
        12  24
         */

        TreeExpressionResolver resolver = new TreeExpressionResolver();
        TreeNode<String, String> root = new TreeNode<String, String>("/", resolver);
        TreeNode<String, String> add = new TreeNode<String, String>("+", resolver);
        root.left = add;
        root.right = new TreeNode<String, String>("3", resolver);
        add.left = new TreeNode<String, String>("12", resolver);
        add.right = new TreeNode<String, String>("24", resolver);
        root.postOrder();
        System.out.println(resolver.getResult());
    }
}
