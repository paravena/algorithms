package graphs;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SnakeAndLadder2 {
    private static final int NUMBER_OF_SQUARES = 100;

    public static void main(String[] args) {
        SnakeAndLadder2 snakeAndLadder = new SnakeAndLadder2();
        snakeAndLadder.readInput(System.in);
    }

    private void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {

        }
    }

    private class TestCase {
        private Map<Integer, Vertex> vertexList;
        private Vertex startVertex;
        private Integer numberOfVertex;

        public TestCase() {
            vertexList = new HashMap<Integer, Vertex>();
            populateVertexList();
        }

        private void populateVertexList() {
            for (int i = 1; i <= NUMBER_OF_SQUARES; i++) {
                vertexList.put(i, new Vertex(i, Integer.MAX_VALUE));
            }
        }

        public void populateEdges(SquarePointPair[] ladders, SquarePointPair[] snakes) {

        }
    }

    private class SquarePointPair {
        private int from;
        private int to;

        public SquarePointPair(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }
    }

    private class Vertex implements Comparable<Vertex> {
        private Integer id;
        private Integer distance;
        private Vertex parent;
        private Map<Vertex, Integer> connectedTo;
        private boolean visited;

        public Vertex(Integer id) {
            this.id = id;
            this.connectedTo = new HashMap<Vertex, Integer>();
        }

        public Vertex(Integer id, Integer distance) {
            this(id);
            this.distance = distance;
        }

        public void addNeighbor(Vertex vertex, Integer weight) {
            connectedTo.put(vertex, weight);
            vertex.connectedTo.put(this, weight);
        }

        public Set<Vertex> getConnections() {
            return connectedTo.keySet();
        }

        public Vertex getParent() {
            return parent;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getWeight(Vertex vertex) {
            return connectedTo.get(vertex);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return !(id != null ? !id.equals(vertex.id) : vertex.id != null);
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }

        @Override
        public int compareTo(Vertex vertex) {
            return this.distance.compareTo(vertex.getDistance());
        }
    }
}