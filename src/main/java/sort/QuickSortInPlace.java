package sort;

import java.io.InputStream;
import java.util.Scanner;

public class QuickSortInPlace {
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int from, int to) {
        if (from < to) {
            int pivotIndex = partition(arr, from, to);
            quickSort(arr, from, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, to);
        }
    }

    private static int partition(int[] arr, int from, int to) {
        int pivot = arr[to];
        int i = from;
        for (int j = from; j < to; j++) {
            if (arr[j] <= pivot) {
                exchange(arr, i, j);
                i++;
            }
        }
        exchange(arr, i, to);
        printArray(arr);

        return i;
    }

    public static void exchange(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] ar) {
        for(int n : ar){
            System.out.print(n + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] arr = readInput(System.in);
        quickSort(arr);
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
