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
        int rowLimit = 0;
        int colLimit = 0;
        int rowMiddleLimit = (M + 1) / 2;
        int colMiddleLimit = (N + 1) / 2;

        for (int i = 0; i <= M - 1; i++) {
            for (int j = 0; j <= N - 1; j++) {
                int i_idx = i;

                if (i_idx >= rowMiddleLimit) {
                    i_idx =  M - 1 - i;
                }

                if (j >= i_idx && j < (M -1 - i_idx)) {
                    currentRow = currentCol = i_idx;
                } else if (j < i_idx) {
                    currentRow = currentCol = j;
                } else if (j > (M - 1 - i_idx)) {
                    currentRow = currentCol = N - j - 1;
                }

                rowLimit = M - currentRow - 1;
                colLimit = N - currentCol - 1;
                //System.out.println("arr["+i+"]["+j+"] belongs to " + currentRow + "," + currentCol + " and " + rowLimit + "," + colLimit);
                printElement(arr, i, j, currentRow, currentCol, rowLimit, colLimit, R);
            }
            System.out.println("");
        }
    }

    private static void printElement(int[][] arr, int i,
                                     int j,
                                     int row,
                                     int col,
                                     int M,
                                     int N,
                                     int R) {
        //System.out.print("arr["+i+"]["+j+"] rotated to ");
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
        //System.out.println("arr["+i+"]["+j+"]");
        System.out.print(arr[i][j] + " ");
    }
}
