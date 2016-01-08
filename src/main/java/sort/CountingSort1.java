package sort;

import java.io.InputStream;
import java.util.Scanner;

public class CountingSort1 {
    private static final int LIMIT = 100;

    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int size = scan.nextInt();
        scan.nextLine();
        int[] arr = new int[size];
        int i = 0;
        while (i < size) {
            int number = scan.nextInt();
            arr[number]++;
            i++;
        }
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < LIMIT; i++) {
            System.out.printf("%d ", arr[i]);

        }
        System.out.println("");
    }
}
