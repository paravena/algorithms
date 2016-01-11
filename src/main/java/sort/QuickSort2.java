package sort;

import java.io.InputStream;
import java.util.Scanner;

public class QuickSort2 {
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int from, int to) {
        if (from < to) {
            int pivotIndex = partition(arr, from, to);
            quickSort(arr, from, pivotIndex - 1);
            printArray(arr, from, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, to);
            printArray(arr, pivotIndex + 1, to);
        }
    }

    private static int partition(int[] arr, int from, int to) {
        int pivotIndex = from;
        int pivot = arr[pivotIndex];
        for (int i = from + 1; i <= to; i++) {
            int current = arr[i];
            if (current <= pivot) {
                if (i == pivotIndex + 1) {
                    exchange(arr, i, pivotIndex++);
                } else {
                    int j = i;
                    while (j > pivotIndex) {
                        exchange(arr, j, j - 1);
                        j--;
                    }
                    pivotIndex++;
                }
            }
        }
        return pivotIndex;
    }

    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        printArray(arr, 0, arr.length - 1);
    }

    public static void printArray(int[] arr, int from, int to) {
        if (from >= to) return;
        for(int i = from; i <= to;  i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] arr = readInput(System.in);
        quickSort(arr);
        printArray(arr);
    }

    private static int[] readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scan.nextInt();
        }
        return arr;
    }
}
