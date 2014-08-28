package util;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class MathUtility {
    // Based on http://csis.pace.edu/~wolf/CS122/infix-postfix.htm
    public static String infixToPosfix(String expression) {
        String result = "";
        Scanner scan = new Scanner(expression);
        Stack<String> stack = new Stack<String>();
        while (scan.hasNext()) {
            String token = scan.next();
            if (isOperator(token) || isParenthesis(token)) {
                if (stack.size() > 0 && !isParenthesis(token)
                        && !isParenthesis(stack.peek())
                        && comparePrecedence(token, stack.peek()) <= 0) {
                    result += " " + stack.pop();
                    while (stack.size() > 0 && !"(".equals(stack.peek())
                            && comparePrecedence(token, stack.peek()) < 0) {
                        result += " " + stack.pop();
                    }
                    stack.push(token);
                } else if (isParenthesis(token)) {
                     if ("(".equals(token)) {
                         stack.push(token);
                     } else {
                         while (!"(".equals(stack.peek())) {
                             result += " " + stack.pop();
                         }
                         stack.pop();
                     }
                } else {
                    stack.push(token);
                }
            } else {
                result += " " + token;
            }
        }

        while(!stack.isEmpty()) {
            result += " " + stack.pop();
        }
        return result;
    }

    public static int comparePrecedence(String opt1, String opt2) {
        int x = 1;
        int y = 1;
        if ("*".equals(opt1) || "/".equals(opt1)) {
            x = 2;
        } else if ("^".equals(opt1)) {
            x = 3;
        }
        if ("*".equals(opt2) || "/".equals(opt2)) {
            y = 2;
        } else if ("^".equals(opt2)) {
            y = 3;
        }
        return x - y;
    }

    public static boolean isNumber(String num) {
        return Pattern.compile("\\d").matcher(num).matches();
    }

    public static boolean isOperator(String opt) {
        return Pattern.compile("[\\+\\-\\*\\/\\^]").matcher(opt).matches();
    }

    public static boolean isParenthesis(String parenthesis) {
        return Pattern.compile("[\\(\\)]").matcher(parenthesis).matches();
    }

    public static void main(String[] args) {

        String p1 = infixToPosfix("A * B + C");
        assert "A B * C +".equals(p1) : p1 + " must be equal to A B * C +";
        System.out.println("A * B + C => " + p1);

        String p2 = infixToPosfix("A + B * C");
        assert "A B C * +".equals(p2) : p2 + " must be equal to A B C * +";
        System.out.println("A + B * C => " + p2);

        String p3 = infixToPosfix("A * ( B + C )");
        assert "A B C + *".equals(p3) : p3 + " must be equal to A B C + *";
        System.out.println("A * (B + C) => " + p3);

        String p4 = infixToPosfix("A - B + C");
        assert "A B - C +".equals(p4) : p4 + " must be equal to A B C + *";
        System.out.println("A - B + C => " + p4);

        String p5 = infixToPosfix("A * B  ^ C + D");
        assert " A B C ^ * D +".equals(p5) : p5 + " must be equal to  A B C ^ * D +";
        System.out.println("A * B ^ C + D => " + p5);

        String p6 = infixToPosfix("A * ( B + C * D ) + E");
        assert "A B C D * + * E +".equals(p5) : p6 + " must be equal to  A B C D * + * E +";
        System.out.println("A * ( B + C * D ) + E => " + p6);
    }
}
