package search;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        public static final char EMPTY = '.';
        public static final char VISITED = 'V';
        public static final char EXIT = '*';
        public static final char TREE = 'X';
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

        public boolean isEmpty(int row, int col) {
            return mazeArr[row][col] == EMPTY || mazeArr[row][col] == EXIT;
        }

        public boolean isExit(int row, int col) {
            return mazeArr[row][col] == EXIT;
        }

        public void countLuck(int K) {
            countLuck(startRow, startCol, Direction.NORTH, K);
        }

        private void countLuck(int currentRow, int currentCol, Direction direction, int k) {
            if (isExit(currentRow, currentCol)) {
                if (k == 0) {
                    System.out.println("Impressed");
                } else {
                    System.out.println("Oops!");
                }
                return;
            }
            mazeArr[currentRow][currentCol] = VISITED;

            switch (direction) {
                case NORTH:
                    while (currentRow > 0
                            && getChoosingPositions(currentRow, currentCol) == null
                            && !isEndPosition(currentRow, currentCol) && !isExit(currentRow, currentCol)) {
                        mazeArr[currentRow--][currentCol] = VISITED;
                    }
                    break;
                case SOUTH:
                    while (currentRow < (mazeArr.length - 1)
                            && getChoosingPositions(currentRow, currentCol) == null
                            && !isEndPosition(currentRow, currentCol) && !isExit(currentRow, currentCol)) {
                        mazeArr[currentRow++][currentCol] = VISITED;
                    }
                    break;
                case WEST:
                    while (currentCol > 0
                            && getChoosingPositions(currentRow, currentCol) == null
                            && !isEndPosition(currentRow, currentCol) && !isExit(currentRow, currentCol)) {
                        mazeArr[currentRow][currentCol--] = VISITED;
                    }

                    break;
                case EAST:
                    while (currentCol < (mazeArr[currentRow].length - 1)
                            && getChoosingPositions(currentRow, currentCol) == null
                            && !isEndPosition(currentRow, currentCol) && !isExit(currentRow, currentCol)) {
                        mazeArr[currentRow][currentCol++] = VISITED;
                    }
                    break;
            }

            List<Position> choosingPositions = getChoosingPositions(currentRow, currentCol);

            if (choosingPositions != null) {
                if (choosingPositions.size() > 1) {
                    for (Position pos : choosingPositions) {
                        countLuck(pos.getRow(), pos.getCol(), pos.getDirection(), k - 1);
                    }
                } else {
                   countLuck(choosingPositions.get(0).getRow(),
                            choosingPositions.get(0).getCol(), choosingPositions.get(0).getDirection(), k);
                }
            } else if (isExit(currentRow, currentCol)) {
                if (k == 0) {
                    System.out.println("Impressed");
                } else {
                    System.out.println("Oops!");
                }
            }
        }

        private boolean isEndPosition(int currentRow, int currentCol) {
            int count = 0;
            if (currentRow > 0
                    && isEmpty(currentRow - 1, currentCol)) {
                count++;
            }

            if (currentRow < (mazeArr.length - 1)
                    && isEmpty(currentRow + 1, currentCol)) {
                count++;
            }

            if (currentCol > 0
                    && isEmpty(currentRow, currentCol - 1)) {
                count++;
            }

            if (currentCol < (mazeArr[currentRow].length - 1)
                    && isEmpty(currentRow, currentCol + 1)) {
                count++;
            }
            return count == 0;
        }

        private List<Position> getChoosingPositions(int currentRow, int currentCol) {
            if (isExit(currentRow, currentCol)) return null;
            List<Position> choosingPositions = new ArrayList<Position>();
            if (currentRow > 0
                    && isEmpty(currentRow - 1, currentCol)) {
                choosingPositions.add(new Position(currentRow - 1, currentCol, Direction.NORTH));
            }

            if (currentRow < (mazeArr.length - 1)
                    && isEmpty(currentRow + 1, currentCol))  {
                choosingPositions.add(new Position(currentRow + 1, currentCol, Direction.SOUTH));
            }

            if (currentCol > 0
                    && isEmpty(currentRow, currentCol - 1)) {
                choosingPositions.add(new Position(currentRow, currentCol - 1, Direction.WEST));
            }

            if (currentCol < (mazeArr[currentRow].length - 1)
                    && isEmpty(currentRow, currentCol + 1)) {
                choosingPositions.add(new Position(currentRow, currentCol + 1, Direction.EAST));
            }
            mazeArr[currentRow][currentCol] = VISITED;
            return choosingPositions.isEmpty() ? null : choosingPositions;
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

    static class Position {
        private int row;
        private int col;
        private Direction direction;

        public Position(int row, int col, Direction direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }

    enum Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }
}
