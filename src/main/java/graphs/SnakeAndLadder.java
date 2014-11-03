package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SnakeAndLadder {
    private Graph graph;
    private Map<Integer, Integer> ladderPositions;
    private Map<Integer, Integer> snakePositions;

    public SnakeAndLadder() {
        graph = new Graph();
        ladderPositions = populateLadderPosition();
        snakePositions = populateSnakePosition();

    }

    private Map<Integer, Integer> populateLadderPosition() {
        Map<Integer, Integer> ladderPositions = new HashMap<Integer, Integer>();
        ladderPositions.put(3, 22);
        ladderPositions.put(5, 8);
        ladderPositions.put(11, 26);
        ladderPositions.put(20, 29);
        return ladderPositions;
    }

    private Map<Integer, Integer> populateSnakePosition() {
        Map<Integer, Integer> snakePositions = new HashMap<Integer, Integer>();
        snakePositions.put(17, 4);
        snakePositions.put(19, 7);
        snakePositions.put(21, 9);
        snakePositions.put(27, 1);
        return snakePositions;
    }

    public void createSnakeAndLadderBoardAsGraph() {
        for (int i = 1; i <= 30; i++) {
            Vertex vertex = new Vertex(i + "");
            graph.addVertex(vertex);
        }

        for (int i = 1; i <= 30; i++) {
            // adding connections
            Vertex vertex = graph.getVertexById(i + "");
            for (int j = 1; j <= 6; j++) {
                int cellIndex = i + j;
                if (cellIndex <= 30) {
                    Vertex toVertex = graph.getVertexById(cellIndex + "");
                    if (ladderPositions.containsKey(cellIndex)) {
                        toVertex = graph.getVertexById(ladderPositions.get(cellIndex) + "");
                        if (toVertex.getDataValue("fromCellId") == null) {
                            toVertex.setDataValue("fromCellId", cellIndex);
                            toVertex.setDataValue("fromLadder", true);
                        }
                    } else if(snakePositions.containsKey(cellIndex)) {
                        toVertex = graph.getVertexById(snakePositions.get(cellIndex) + "");
                        if (toVertex.getDataValue("fromCellId") == null) {
                            toVertex.setDataValue("fromSnake", true);
                        }
                    }
                    vertex.addNeighbor(toVertex);
                }
            }
        }
    }

    public void determineMinimumNumberOfDiceThrows() {
        Stack<Vertex> stack = new Stack<Vertex>();
        Vertex start = graph.getVertexById("1");
        graph.bfs(start);
        Vertex currentVertex = graph.getVertexById("30");
        stack.push(currentVertex);
        while (currentVertex != null) {
            currentVertex = currentVertex.getParent();
            if (currentVertex != null) stack.push(currentVertex);
        }
        Vertex cell = stack.pop();
        int fromCellNumber = Integer.parseInt(cell.getId());
        System.out.println("Start at cell " + fromCellNumber);
        while (!stack.isEmpty()) {
            cell = stack.pop();
            int toCellNumber = Integer.parseInt(cell.getId());
            boolean jumpToLadder = false;
            int jumpToCellNumber = toCellNumber;
            if (cell.getDataValue("fromCellId") != null && cell.getDataValue("fromLadder") != null) {
                toCellNumber = (Integer) cell.getDataValue("fromCellId");
                jumpToLadder = true;
            }
            int diceNumber = toCellNumber - fromCellNumber;
            System.out.printf("Throws %s and go to cell %s\n", diceNumber, toCellNumber);
            if (jumpToLadder) System.out.println("Jump to cell position " + jumpToCellNumber);
            fromCellNumber = Integer.parseInt(cell.getId());

        }
    }

    public static void main(String[] args) {
        SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
        snakeAndLadder.createSnakeAndLadderBoardAsGraph();
        snakeAndLadder.determineMinimumNumberOfDiceThrows();
    }
}
