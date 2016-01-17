package strings;

import java.util.Scanner;

public class Pangrams {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        isPangram(line);
    }

    private static void isPangram(String line) {
        int[] alphabet = new int['z' - 'a' + 1];
        String phrase = line.toLowerCase();
        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);
            if (c == ' ' || c == '\n') continue;
            alphabet[c - 'a']++;
        }
        for (int n : alphabet) {
            if (n == 0) {
                System.out.println("not pangram");
                return;
            }
        }
        System.out.println("pangram");
    }
}
