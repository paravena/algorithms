package implementation;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixRotation {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int M = scan.nextInt();
        int N = scan.nextInt();
        int R = scan.nextInt();
        scan.nextLine();
        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            String[] line = scan.nextLine().split("\\s");
            arr[i] = new int[N];
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        printArray(arr);
        rotateArray(arr, M, N, R);
        printArray(arr);
    }

    private static void printArray(int[][] arr) {
        for (int[] first : arr) {
            for (int second : first) {
                System.out.print(second + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static void rotateArray(int[][] arr, int M, int N, int R) {
        int rowLimit = (M + 1) / 2;
        int colLimit = (N + 1) / 2;
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                if (i == j) {
                    rotateRow(arr, i, j, M, N);
                }
            }
        }
    }

    private static void rotateRow(int[][] arr, int row, int col, int M, int N) {
        int first = arr[row][col];
        int i = row;
        int j = col;
        int L = arr.length;

    }
}
