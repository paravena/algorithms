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
        int rowMiddleLimit = (M + 1) / 2;
        int colMiddleLimit = (N + 1) / 2;
        int maxRows = M - 1;
        int maxColumns = N - 1;

        for (int i = 0; i <= maxRows; i++) {
            for (int j = 0; j <= maxColumns; j++) {
               int i_idx = i;
                if (i_idx >= rowMiddleLimit) {
                    i_idx =  maxRows - i;
                }

                if (j >= i_idx && j <= (maxColumns - i_idx)) {
                    currentRow = currentCol = i_idx;
                } else if (j < i_idx) {
                    currentRow = currentCol = j;
                    if (j == colMiddleLimit) {
                        currentRow = currentCol = j - 1;
                    }
                } else if (j >= (maxColumns - i_idx)) {
                    currentRow = currentCol = maxColumns - j;
                }

                int rowLimit = maxRows - currentRow;
                int colLimit = maxColumns - currentCol;
                //System.out.println("arr["+i+"]["+j+"] belongs to " + currentRow + "," + currentCol + " and " + rowLimit + "," + colLimit);
                printElement(arr, i, j, currentRow, currentCol, rowLimit, colLimit, R);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private static void printElement(int[][] arr, int i,
                                     int j,
                                     int row,
                                     int col,
                                     int M,
                                     int N,
                                     int R) {
        int H = M - row + 1;
        int W = N - col + 1;
        int L = (W + (H - 2)) * 2;
        if (R >= L) {
            R = R % L;
        }
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
