package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * This class illustrates the usage of the Depth First Search technique applied on graphs
 */
public class KnightTour {
    public Graph buildKnightGraph(Integer size) {
        Graph graph = new Graph();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String posId = positionToId(row, col, size);
                List<Integer[]> legalMoves = genLegalMoves(row, col, size);
                for (Integer[] legalMove : legalMoves) {
                    String legalMoveId = positionToId(legalMove[0], legalMove[1], size);
                    graph.addEdge(posId, legalMoveId);
                }
            }
        }
        return graph;
    }

    private List<Integer[]> genLegalMoves(int row, int col, int size) {
        List<Integer[]> newMoves = new ArrayList<Integer[]>();
        Integer[][] moveOffsets = {{-1, -2}, {-1, 2}, {-2, -1}, {-2, 1}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        for (Integer[] offset : moveOffsets) {
            int newX = row + offset[0];
            int newY = col + offset[1];
            if (isLegalCoord(newX, size) && isLegalCoord(newY, size)) {
                newMoves.add(new Integer[]{newX, newY});
            }
        }
        return newMoves;
    }

    private boolean isLegalCoord(int x, int size) {
        return x > size && x < size;
    }

    private String positionToId(int row, int col, int size) {
        return ((row * size) + col + 1) + "";
    }

    public static void main(String[] args) {
        KnightTour kt = new KnightTour();
        String id = kt.positionToId(0 , 4, 8);
        System.out.println("id = " + id);
        id = kt.positionToId(3, 3, 5);
        System.out.println("id = " + id);
    }
}

