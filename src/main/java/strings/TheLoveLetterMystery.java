package strings;

import java.io.InputStream;
import java.util.Scanner;

public class TheLoveLetterMystery {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String original = scan.nextLine();
            int count = countNumberOfOperationsToPalindrome(original);
            System.out.println(count);
        }
    }

    private static int countNumberOfOperationsToPalindrome(String original) {
        int count = 0;
        int i = 0;
        int j = original.length() - 1;
        while (j > i) {
            char c1 = original.charAt(i);
            char c2 = original.charAt(j);
            count += Math.abs(c2 - c1);
            i++;
            j--;
        }
        return count;
    }
}
