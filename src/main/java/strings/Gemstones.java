package strings;


import java.io.InputStream;
import java.util.Scanner;

public class Gemstones {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int[] elements = new int['z' - 'a' + 1];
        int T = scan.nextInt();
        scan.nextLine();
        for (int i = 1; i <= T; i++) {
            String word = scan.nextLine();
            String temp = "";
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (temp.indexOf(c) == -1 && elements[c - 'a'] < i) {
                    elements[c - 'a']++;
                }
                temp += c;
            }
        }
        int result = 0;
        for (int counter : elements) {
            if (counter == T) {
                result++;
            }
        }
        System.out.println(result);
    }
}
