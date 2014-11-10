package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This class illustrates the usage of the Depth First Search technique applied on graphs
 */
public class KnightTour {
    public Graph buildKnightGraph(Integer size) {
        Graph graph = new Graph();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String posId = positionToId(row, col, size);
                System.out.println("moving to position " + posId + " (" +row+ ", "+col+")");
                List<Integer[]> legalMoves = genLegalMoves(row, col, size);
                for (Integer[] legalMove : legalMoves) {
                    String legalMoveId = positionToId(legalMove[0], legalMove[1], size);
                    System.out.println("adding legal move from " + posId + " to " + legalMoveId);
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

    /**
     * Depth first search algorithm for implementing the tour problem
     *
     * @param n the current depth in the search tree
     * @param path a list of vertices visited up to this point
     * @param u the vertex in the graph we wish to explore
     * @param limit the number of nodes in the path
     */
    public boolean knightTour(int n, LinkedList<Vertex> path, Vertex u, int limit) {
        System.out.println("visiting node position " + u.getId());
        boolean done = false;
        u.setColor("gray");
        path.add(u);
        if (n < limit) {
            for (Vertex node : u.getConnections()) {
                if ("white".equals(node.getColor())) {
                    done = knightTour(n + 1, path, node, limit);
                }
                if (done) {
                    break;
                }
            }
            if (!done) { // prepare to backtrack
                System.out.println("backtracking node position " + u.getId());
                path.removeLast();
                u.setColor("white");
            }
        } else {
            done = true;
        }
        return done;
    }

    private boolean isLegalCoord(int x, int size) {
        return x >= 0 && x < size;
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
        Graph graph = kt.buildKnightGraph(8);
        Vertex vertex = graph.getVertexById("1");
        System.out.println(vertex);
        //kt.knightTour(0, new LinkedList<Vertex>(), graph.getVertexById("1"), 64);
    }
}

