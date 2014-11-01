package util;

import java.util.Stack;

public class ConvertorUtility {
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static String convertDecimalToBase(int number, int base) {
        String result = "";
        Stack<Character> stack = new Stack<Character>();
        while (number != 0) {
            int restIndex = number % base;
            stack.push(digits[restIndex]);
            number /= base;
        }
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println("1323 in binary is " + convertDecimalToBase(1323, 2));
        System.out.println("1323 in octal is " + convertDecimalToBase(1323, 8));
        System.out.println("1323 in hexadecimal is " + convertDecimalToBase(1323, 16));
    }
}
