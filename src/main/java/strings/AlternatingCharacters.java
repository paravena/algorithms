package strings;

import java.io.InputStream;
import java.util.Scanner;

public class AlternatingCharacters {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String word = scan.nextLine();
            System.out.println(numberOfDeletions(word));
        }
    }

    private static int numberOfDeletions(String word) {
        int i = 0;
        int count = 0;
        while (i + 1 < word.length()) {
            char current = word.charAt(i);
            char next = word.charAt(i + 1);
            if (current == next) {
                count++;
            }
            i++;
        }
        return count;
    }
}
