package sort;

import java.io.InputStream;
import java.util.Scanner;

public class BiggerIsGreater {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String word = scan.nextLine();
            printNextLexicographicallyWord(word);
        }
    }

    private static boolean printNextLexicographicallyWord(String word) {
        char[] arr = word.toCharArray();
        int L = arr.length;
        int i = L - 1;
        // Finding the non-increasing suffix
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }
        if (i <= 0) {
            System.out.println("no answer");
            return false;
        }

        int pivotIndex = i - 1;
        // Find successor to pivot
        int j = L - 1;
        while (arr[pivotIndex] > arr[j]) {
            j--;
        }
        swapCharacters(arr, pivotIndex, j);
        // Reverse the suffix
        j = L - 1;
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        System.out.println(new String(arr));
        return true;
    }

    private static void swapCharacters(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
