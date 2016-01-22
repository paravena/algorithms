package strings;

import java.io.InputStream;
import java.util.Scanner;

public class TwoStrings {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String word1 = scan.nextLine();
            String word2 = scan.nextLine();
            areTwoStrings(word1, word2);
        }
    }

    private static void areTwoStrings(String word1, String word2) {
        int[] alphabet = new int['z'- 'a' + 1];

        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i);
            int idx = c1 - 'a';
            if (alphabet[idx] == 0) {
                alphabet[idx]++;
            }
        }

        for (int i = 0; i < word2.length(); i++) {
            char c2 = word2.charAt(i);
            int idx = c2 - 'a';
            if (alphabet[idx] == 1) {
                alphabet[c2 - 'a']++;
            }
        }

        for (int i : alphabet) {
            if (i > 1) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
