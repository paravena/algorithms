package search;

import java.io.InputStream;
import java.util.Scanner;

public class CountLuck {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int T = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < T; i++) {
            int N = scan.nextInt();
            int M = scan.nextInt();
            scan.nextLine();
            Maze maze = initializeMaze(N, M, scan);
            int K = scan.nextInt();
            maze.countLuck(K);
            maze.showMaze();
            scan.nextLine();
        }
    }

    private static Maze initializeMaze(int N, int M, Scanner scan) {
        Maze maze = new Maze(N, M);
        for (int i = 0; i < N; i++) {
            char[] line = scan.nextLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char ch = line[j];
                maze.populate(i, j, ch);
            }
        }
        return maze;
    }

    private static class Maze {
        private char[][] mazeArr;
        private int startRow;
        private int startCol;

        public Maze(int N, int M) {
            mazeArr = new char[N][M];
        }

        public void populate(int i, int j, char value) {
            mazeArr[i][j] = value;
            if (value == 'M') {
                startRow = i;
                startCol = j;
            }
        }

        public boolean isTree(int row, int col) {
            return mazeArr[row][col] == 'X';
        }

        public boolean isEmpty(int row, int col) {
            return mazeArr[row][col] == '.';
        }

        public boolean isExit(int row, int col) {
            return mazeArr[row][col] == '*';
        }


        public void countLuck(int k) {

        }

        public void showMaze() {
            for (int i = 0; i < mazeArr.length; i++) {
                for (int j = 0; j < mazeArr[i].length; j++) {
                    System.out.print(mazeArr[i][j]);
                }
                System.out.println("");
            }
        }
    }
}
