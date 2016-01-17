package strings;

import java.io.InputStream;
import java.util.Scanner;

public class FunnyString {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String word = scan.nextLine();
            isFunnyString(word);
        }
    }

    private static void isFunnyString(String word) {
        int i = 0;
        int j = word.length() - 1;
        boolean funny = true;
        while (i < word.length() - 1) {
            if (Math.abs(word.charAt(i + 1) - word.charAt(i))
                    != Math.abs(word.charAt(j - 1) - word.charAt(j))) {
                funny = false;
                break;
            }

            i++;
            j--;
        }
        if (funny) {
            System.out.println("Funny");
        } else {
            System.out.println("Not Funny");
        }
    }

}
