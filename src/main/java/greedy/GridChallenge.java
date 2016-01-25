package greedy;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int T = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < T; i++) {
            int N = scan.nextInt();
            scan.nextLine();
            validateGrid(N, scan);
        }
    }

    private static void validateGrid(int N, Scanner scan) {
        char[][] arr = new char[N][];
        for (int i = 0; i < arr.length; i++) {
            char[] word = scan.nextLine().toCharArray();
            Arrays.sort(word);
            arr[i] = word;
        }
        boolean isSorted = true;
        for (int j = 0; j < N; j++) {
            char previous = arr[0][j];
            for (int i = 1; i < N;i++) {
                char current = arr[i][j];
                if (current < previous) {
                    isSorted = false;
                    System.out.println("NO");
                    return;
                }
                previous = current;
            }
        }
        System.out.println("YES");
    }
}
