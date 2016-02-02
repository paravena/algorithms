package sort;

import java.io.InputStream;
import java.util.Scanner;

public class SampleChallenge {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int V = scan.nextInt();
        scan.nextLine();
        int N = scan.nextInt();
        scan.nextLine();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        search(V, arr);
    }

    private static void search(int V, int[] arr) {
        search(V, arr, 0, arr.length - 1);
    }

    private static void search(int v, int[] arr, int lo, int hi) {
        if (lo > hi) return;
        int mid = lo + (hi - lo) / 2;
        if (v == arr[mid]) {
            System.out.println(mid);
        } else if (v < arr[mid]) {
            search(v, arr, lo, mid - 1);
        } else {
            search(v, arr, mid + 1, hi);
        }
     }
}
