package chapter01;

public class EvaluatePostfix {
    public Double evaluate(String expression) {
        FixedArrayStack<String> stack = new FixedArrayStack<String>();
        Double operand1;
        Double operand2;
        Double result = 0.0;
        String[] elements = expression.split("\\s+");
        for (String element : elements) {
            if ("+".equals(element) || "-".equals(element) || "/".equals(element) || "*".equals(element)) {
                operand2 = Double.parseDouble(stack.pop());
                operand1 = Double.parseDouble(stack.pop());
                if ("+".equals(element)) {
                    result = operand1 + operand2;
                    System.out.println(operand1 + " + " + operand2 + " = " + result);
                } else if ("-".equals(element)) {
                    result = operand1 - operand2;
                    System.out.println(operand1 + " - " + operand2 + " = " + result);
                } else if ("/".equals(element)) {
                    result = operand1 / operand2;
                    System.out.println(operand1 + " / " + operand2 + " = " + result);
                } else if ("*".equals(element)) {
                    result = operand1 * operand2;
                    System.out.println(operand1 + " * " + operand2 + " = " + result);
                }
                stack.push(result.toString());
            } else {
                stack.push(element);
            }
        }
        return Double.parseDouble(stack.pop());
    }

    public static void main(String[] args) {
        EvaluatePostfix ep = new EvaluatePostfix();
        Double result = ep.evaluate("6 2 / 5 +");
        System.out.println("result = " + result);
        result = ep.evaluate("4 5 + 7 2 -  *");
        System.out.println("result = " + result);
    }
}
