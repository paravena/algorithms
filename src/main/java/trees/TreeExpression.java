package trees;

import basic.LinkedList;

import java.util.Stack;

public class TreeExpression {
    private String expression;
    private TreeNode<Character> root;

    public TreeExpression(String expression) {
        this.expression = expression;
        root = parse(expression);
    }

    private TreeNode<Character> parse(String expression) {
        String n = "";
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < expression.length(); i++) {
            Character c = expression.charAt(i);
            if (isNumber(c)) {
                n += c;
                continue;
            } else {
                if (n.length() > 0) {
                    numbers.add(Integer.parseInt(n));
                    n = "";
                }
                if (isOperator(c)) {
                    stack.push(c);
                }
            }
        }
        return null;
    }

    private boolean isOperator(Character c) {
        return c.equals('+') || c.equals('-') || c.equals('*') || c.equals('/');
    }

    private boolean isNumber(Character c) {
        return c >= '0' && c <= '9';
    }


    public static void main(String[] args) {
        //        new TreeExpression("12 + 24 / 3");
        TreeExpressionResolver resolver = new TreeExpressionResolver();
        TreeNode<String> root = new TreeNode<String>("/", resolver);
        TreeNode<String> add = new TreeNode<String>("+", resolver);
        root.left = add;
        root.right = new TreeNode<String>("3", resolver);
        add.left = new TreeNode<String>("12", resolver);
        add.right = new TreeNode<String>("24", resolver);
        root.postorder();
        System.out.println(resolver.getResult());
    }
}

class TreeExpressionResolver implements TreeNodeVisitor<String> {
    private Stack<Double> numbers = new Stack<Double>();
    private Double result;

    @Override
    public void visit(String element) {
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
}
