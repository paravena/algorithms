package sort;

import java.io.InputStream;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int L = scan.nextInt();
        scan.nextLine();
        int[] arr = new int[L];
        for (int i = 0; i < L; i++) {
            arr[i] = scan.nextInt();
        }
        insertionSort(arr);
    }

    private static void insertionSort(int[] arr) {
        if (arr == null) return;
        if (arr.length == 0 || arr.length == 1) return;
        int L = arr.length;
        int i = L - 2;
        int last = arr[L - 1];
        while (i >= 0 && last < arr[i]) {
            arr[i + 1] = arr[i];
            printArray(arr);
            i--;
        }
        arr[i + 1] = last;
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int elem : arr) {
            System.out.print(elem + " ");

        }
        System.out.println("");
    }
}
