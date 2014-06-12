package chapter01;

import java.util.Arrays;

public class Excersice1_1_22 {
    public static int rank(int[] a, int k) {
        return rank(a, k, 0, a.length - 1);
    }
    public static int rank(int[] a, int k, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (k < a[mid]) {
            return rank(a, k, lo, mid - 1);
        } else if (k > a[mid]) {
            return rank(a, k, mid + 1, hi);
        } else {
            return mid;
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 22, 56, 89};
        int index = rank(arr, 56);
        System.out.println("index = " + index);
    }
}
