package collections;

import java.util.Arrays;
import java.util.Scanner;

public class NegativeSubArrays {
    public static void main(String[] args) {
        int[] input = readInput();
        int count = countNegativeSubArrays(input);
        System.out.println(count);
    }

    public static int[] readInput() {
        Scanner scan = new Scanner(System.in);
        int length = scan.nextInt();
        int[] input =  new int[length];
        scan.nextLine();
        String numbers = scan.nextLine();
        String[] tokens = numbers.split("\\s");
        if (tokens.length < length) {
            throw new RuntimeException("Numbers provided are less than " + length);
        }
        int i = 0;
        for (String token : tokens) {
            input[i++] = Integer.valueOf(token);
        }
        return input;
    }

    private static int countNegativeSubArrays(int[] input) {
        int count = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                int[] candidate = Arrays.copyOfRange(input, i, j + 1);
                if (isNegativeSubArray(candidate)) count++;
            }
        }
        return count;
    }

    private static boolean isNegativeSubArray(int[] candidate) {
        int sum = 0;
        for (int element : candidate) {
            sum += element;
        }
        return sum < 0;
    }
}
