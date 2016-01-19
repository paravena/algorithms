package implementation;

import java.io.InputStream;
import java.util.Scanner;

public class MatrixRotation2 {
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
        rotateArray(arr, M, N, R);
    }

    private static void rotateArray(int[][] arr, int M, int N, int R) {
        int currentRow = 0;
        int currentCol = 0;
        int rowLimit = M - 1;
        int colLimit = N - 1;
        int rowNestedLimit = (M + 1) / 2;
        int colNestedLimit = (N + 1) / 2;

        for (int i = 0; i < M - 1; i++) {
            for (int j = 0; j < N - 1; j++) {
                printElement(arr, i, j, currentRow, currentCol, rowLimit, colLimit, R);


                if (i == j) {
                    currentRow = i;
                    currentCol = i;
                }
            }
        }
        /*
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                if (i == j) {
                    rotateRow(arr, i, j, M - i, N - i, R);
                }
            }
        }
        */
    }

    private static void rotateRow(int[][] arr, int row, int col, int M, int N, int R) {
        int i = row;
        int j = col;
        for (; j < N - 1; j++) {
            printElement(arr, row, j, row, col, M - 1, N - 1, R);
        }

        for (; i < M - 1; i++) {
            printElement(arr, i, j, row, col, M - 1, N - 1, R);
        }

        for (; j > col; j--) {
            printElement(arr, i, j, row, col, M - 1, N - 1, R);
        }

        for (; i > row; i--) {
            printElement(arr, i, j, row, col, M - 1, N - 1, R);
        }
    }


    private static void printElement(int[][] arr, int i,
                                     int j,
                                     int row,
                                     int col,
                                     int M,
                                     int N,
                                     int R) {
        int counter = 0;
        while (counter < R) {
            if ((j >= col && j <= N) && i == row) {
                int offset = N - j;
                if ((counter + offset) <= R) {
                    j += offset;
                    counter += offset;
                } else {
                    j += (R - counter);
                    break;
                }
            }

            if (j == N && (i >= row && i <= M)) {
                int offset = M - i;
                if ((counter + offset) <= R) {
                    i += offset;
                    counter += offset;
                } else {
                    i += (R - counter);
                    break;
                }
            }

            if ((j >= col && j <= N) && i == M) {
                int offset = j - col;
                if ((counter + offset) <= R) {
                    j -= offset;
                    counter += offset;
                } else {
                    j -= (R - counter);
                    break;
                }
            }

            if (j == col && (i >= row && i <= M)) {
                int offset = i - row;
                if ((counter + offset) <= R) {
                    i -= offset;
                    counter += offset;
                } else {
                    i -= (R - counter);
                    break;
                }
            }
        }

        System.out.print(arr[i][j] + " ");
    }
}
