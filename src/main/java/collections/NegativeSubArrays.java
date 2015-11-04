package collections;

import java.util.Scanner;

public class NegativeSubArrays {
    public static void main(String[] args) {
        readInput();
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
}
