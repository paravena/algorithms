package search;

import javax.annotation.PostConstruct;
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

        public boolean isTree(int row, int col) {
            return mazeArr[row][col] == TREE;
        }

        public boolean isEmpty(int row, int col) {
            return mazeArr[row][col] == EMPTY;
        }

        public boolean isExit(int row, int col) {
            return mazeArr[row][col] == EXIT;
        }

        public void countLuck(int K) {
            int result = countLuck(startRow, startCol, Direction.NORTH);
            if (result == -1) {
                result = countLuck(startRow, startCol, Direction.EAST);
            }
            if (result == -1) {
                result = countLuck(startRow, startCol, Direction.SOUTH);
            }
            if (result == -1) {
                result = countLuck(startRow, startCol, Direction.WEST);
            }
            if (result == K) {
                System.out.println("Impressed");
            } else {
                System.out.println("Oops!");
            }
        }

        public int countLuck(int startRow, int startCol, Direction direction) {
            // From start move to north until there is a choosing position
            mazeArr[startRow][startCol] = VISITED;
            return walkUntilChoosingPositionOrExit(startRow, startCol, direction);
        }

        private int walkUntilChoosingPositionOrExit(int currentRow, int currentCol, Direction direction) {
            int result = 0;
            switch (direction) {
                case NORTH:
                    while (currentRow > 0
                            && !isChoosingPosition(currentRow, currentCol, direction)
                            && !isEndPosition(currentRow, currentCol)) {
                        if (isExit(currentRow, currentCol)) {
                            return 0;
                        }
                        mazeArr[currentRow--][currentCol] = VISITED;
                    }
                    break;
                case SOUTH:
                    while (currentRow < (mazeArr.length - 1)
                            && !isChoosingPosition(currentRow, currentCol, direction)
                            && !isEndPosition(currentRow, currentCol)) {
                        if (isExit(currentRow, currentCol)) {
                            return 0;
                        }
                        mazeArr[currentRow++][currentCol] = VISITED;
                    }
                    break;
                case WEST:
                    while (currentCol > 0
                            && !isChoosingPosition(currentRow, currentCol, direction)
                            && !isEndPosition(currentRow, currentCol)) {
                        if (isExit(currentRow, currentCol)) {
                            return 0;
                        }
                        mazeArr[currentRow][currentCol--] = VISITED;
                    }

                    break;
                case EAST:
                    while (currentCol < (mazeArr[currentCol].length - 1)
                            && !isChoosingPosition(currentRow, currentCol, direction)
                            && !isEndPosition(currentRow, currentCol)) {
                        if (isExit(currentRow, currentCol)) {
                            return 0;
                        }
                        mazeArr[currentRow][currentCol++] = VISITED;
                    }
                    break;
            }
            if (!isEndPosition(currentRow, currentCol)) {
                result = -1;
            } else if (isChoosingPosition(currentRow, currentCol, direction)) {
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    result += countLuck(currentRow, currentCol, Direction.WEST) > 0 ? 1 : 0;
                    result += countLuck(currentRow, currentCol, Direction.EAST) > 0 ? 1 : 0;
                } else {
                    result += countLuck(currentRow, currentCol, Direction.NORTH) > 0 ? 1 : 0;
                    result += countLuck(currentRow, currentCol, Direction.SOUTH) > 0 ? 1 : 0;
                }
            }
            return result;
        }

        private boolean isEndPosition(int currentRow, int currentCol) {
            int count = 0;
            if (currentRow > 0
                    && mazeArr[currentRow - 1][currentCol] == EMPTY) {
                count++;
            }

            if (currentRow < (mazeArr.length - 1)
                    && mazeArr[currentRow + 1][currentCol] == EMPTY) {
                count++;
            }

            if (currentCol > 0
                    && mazeArr[currentRow][currentCol - 1] == EMPTY) {
                count++;
            }

            if (currentCol < (mazeArr[currentRow].length - 1)
                    && mazeArr[currentRow][currentCol + 1] == EMPTY) {
                count++;
            }
            return count > 0;
        }

        private List<Position> isChoosingPosition(int currentRow, int currentCol, Direction direction) {
            List<Position> choosingPositions = new ArrayList<Position>();
            if (direction != Direction.NORTH
                    && currentRow > 0
                    && mazeArr[currentRow - 1][currentCol] == EMPTY) {
                choosingPositions.add(new Position(currentRow - 1, currentCol, Direction.NORTH));
            }

            if (direction != Direction.SOUTH
                    && currentRow < (mazeArr.length - 1)
                    && mazeArr[currentRow + 1][currentCol] == EMPTY) {
                choosingPositions.add(new Position(currentRow + 1, currentCol, Direction.SOUTH));
            }

            if (direction != Direction.WEST
                    && currentCol > 0
                    && mazeArr[currentRow][currentCol - 1] == EMPTY) {
                choosingPositions.add(new Position(currentRow, currentCol - 1, Direction.WEST));
            }

            if (direction != Direction.EAST
                    && currentCol < (mazeArr[currentRow].length - 1)
                    && mazeArr[currentRow][currentCol + 1] == EMPTY) {
                choosingPositions.add(new Position(currentRow, currentCol + 1, Direction.EAST));
            }
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
