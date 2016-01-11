package sort;

import java.io.InputStream;
import java.util.Scanner;

public class QuickSortPartition {
    public static void partition(int[] arr) {
        int p = arr[0];
        int leftMark = 1;
        int rightMark = arr.length - 1;

        while (leftMark <= rightMark) {
            while (p >= arr[leftMark] && rightMark >= leftMark) leftMark++;
            while (p <= arr[rightMark] && rightMark >= leftMark) rightMark--;
            if (rightMark <= leftMark) break;
            exchange(arr, leftMark, rightMark);
        }
        arr[0] = arr[rightMark];
        arr[rightMark] = p;
    }

    private static void exchange(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void printArray(int[] ar) {
        for(int n : ar){
            System.out.print(n + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] ar = readInput(System.in);
        partition(ar);
        printArray(ar);
    }

    private static int[] readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int n = scan.nextInt();
        int[] ar = new int[n];
        for(int i = 0;i < n;i++){
            ar[i] = scan.nextInt();
        }
        return ar;
    }
}
