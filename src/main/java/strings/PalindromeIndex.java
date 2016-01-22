package strings;

import java.io.InputStream;
import java.util.Scanner;

public class PalindromeIndex {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int T = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < T; i++) {
            String word = scan.nextLine();
            printIndex(word);
        }
    }

    private static void printIndex(String word) {
        int i = 0;
        int j = word.length() - 1;
        int idx = -1;
        while (i < j) {
            char c1 = word.charAt(i);
            char c2 = word.charAt(j);
            if (c1 != c2) {
                if (i < (word.length() - 1) && word.charAt(i + 1) == c2) {
                    idx = i++;
                    continue;
                } else if (j > 0 && word.charAt(j - 1) == c1) {
                    idx = j--;
                    continue;
                }
            }
            i++;
            j--;
        }
        System.out.println(idx);
    }
}
