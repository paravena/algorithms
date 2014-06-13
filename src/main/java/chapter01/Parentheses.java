package chapter01;

public class Parentheses {
    public boolean validate(String expression) {
        FixedArrayStack<Character> stack = new FixedArrayStack<Character>(20);
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if (c == ']' || c == '}' || c == ')') {
                Character p = stack.pop();
                if (c == ']' && '[' != p) return false;
                else if (c == '}' && '{' != p) return false;
                else if (c == ')' && '(' != p) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Parentheses parentheses = new Parentheses();
        boolean isValid = parentheses.validate("[{}]{}[([][])]");
        System.out.println("isValid = " + isValid);
    }
}
