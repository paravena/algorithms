package implementation;


import java.io.InputStream;
import java.util.Scanner;

public class CavityMap {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan =  new Scanner(in);
        int n = scan.nextInt();
        char [][]arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            String lines = scan.next();
            arr[i] = lines.toCharArray();
        }

        for (int i = 1; i < n-1; i++) {
            for(int j = 1; j < n-1; j++) {
                if((arr[i][j] > arr[i-1][j]) && (arr[i][j] > arr[i+1][j])
                        && (arr[i][j] > arr[i][j-1]) && (arr[i][j] > arr[i][j+1]))
                    arr[i][j] = 'X';
            }
        }

        printResult(arr);
    }

    private static void printResult(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
