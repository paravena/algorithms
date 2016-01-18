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
        for (int i = 0; i < R; i++) {
            rotateArray(arr, M, N);
        }
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

    private static void rotateArray(int[][] arr, int M, int N) {
        int rowLimit = (M + 1) / 2;
        int colLimit = (N + 1) / 2;
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                if (i == j) {
                    rotateRow(arr, i, j, M - i, N - i);
                }
            }
        }
    }

    private static void rotateRow(int[][] arr, int row, int col, int M, int N) {
        int current = arr[row][col];
        int i = row;
        int j = col;
        int previous = arr[row][col + 1];
        i++;
        while (i <= M - 1) {
            int temp = arr[i][j];
            arr[i][j] = current;
            current = temp;
            i++;
        }
        i = M - 1;
        j++;
        while (j <= N - 1) {
            int temp = arr[i][j];
            arr[i][j] = current;
            current = temp;
            j++;
        }
        j = N - 1;
        i--;
        while(i > row) {
            int temp = arr[i][j];
            arr[i][j] = current;
            current = temp;
            i--;
        }
        i = row;
        while(j > col) {
            int temp = arr[i][j];
            arr[i][j] = current;
            current = temp;
            j--;
        }
        arr[row][col] = previous;
    }
}
