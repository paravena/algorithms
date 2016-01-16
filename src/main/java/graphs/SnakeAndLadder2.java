package graphs;

import java.io.InputStream;
import java.util.*;

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
            TestCase testCase = readTestCase(scan);
            testCase.shortestPath();
            testCase.printResult();
        }
    }

    private TestCase readTestCase(Scanner scan) {
        TestCase testCase = new TestCase();
        int L = scan.nextInt();
        scan.nextLine();
        Map<Integer, SquarePointPair> ladders = new HashMap<Integer, SquarePointPair>(L);
        for (int i = 0; i < L; i++) {
            String[] tokens = scan.nextLine().split("\\s");
            ladders.put(Integer.valueOf(tokens[0]),
                    new SquarePointPair(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])));
        }
        int S = scan.nextInt();
        scan.nextLine();
        Map<Integer, SquarePointPair> snakes = new HashMap<Integer, SquarePointPair>(S);
        for (int i = 0; i < S; i++) {
            String[] tokens = scan.nextLine().split("\\s");
            snakes.put(Integer.valueOf(tokens[0]),
                    new SquarePointPair(Integer.valueOf(tokens[0]), Integer.valueOf(tokens[1])));
        }
        testCase.populateEdges(ladders, snakes);
        return testCase;
    }

    private class TestCase {
        private Map<Integer, Vertex> vertexList;
        private Vertex startVertex;
        private Integer numberOfVertex;

        public TestCase() {
            vertexList = new HashMap<Integer, Vertex>();
            numberOfVertex = NUMBER_OF_SQUARES;
            populateVertexList();
            startVertex = vertexList.get(1);
        }

        public void populateVertexList() {
            for (int i = 1; i <= numberOfVertex; i++) {
                vertexList.put(i, new Vertex(i, Integer.MAX_VALUE));
            }
        }

        public void populateEdges(Map<Integer, SquarePointPair> ladders, Map<Integer, SquarePointPair> snakes) {
            for (int i = 1; i <= numberOfVertex; i++) {
                Vertex currentVertex = vertexList.get(i);
                addNeighborsToVertex(currentVertex, ladders, snakes);
            }
        }

        private void addNeighborsToVertex(Vertex currentVertex,
                                          Map<Integer, SquarePointPair> ladders,
                                          Map<Integer, SquarePointPair> snakes) {
            Integer vertextId = currentVertex.getId();
            for (int i = 1; i <= 6; i++) {
                Vertex destVertex = null;
                Integer destId = vertextId + i;
                if (ladders.get(destId) != null) {
                    destId = ladders.get(destId).to;
                } else if (snakes.get(destId) != null) {
                    destId = snakes.get(destId).to;
                }
                if (destId <= numberOfVertex) {
                    destVertex = vertexList.get(destId);
                    currentVertex.addNeighbor(destVertex, 1);
                }
            }
        }

        public void shortestPath() {
            PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
            startVertex.setDistance(0);
            pq.addAll(vertexList.values());

            while (!pq.isEmpty()) {
                Vertex current = pq.remove();
                if (current.getDistance() == Integer.MAX_VALUE) {
                    continue; // unreachable vertex
                }
                for (Vertex next : current.getConnections()) {
                    Integer newDistance = current.getDistance() + current.getWeight(next);
                    if (newDistance < next.getDistance()) {
                        next.setDistance(newDistance);
                        next.setParent(current);
                        pq.remove(next);
                        pq.add(next);
                    }
                }
            }
        }

        public void printResult() {
            Vertex currentVertex = vertexList.get(100);
            int steps = 0;
            while (currentVertex.getParent() != null) {
                //System.out.println(currentVertex.getId() + " ");
                currentVertex = currentVertex.getParent();
                steps++;
            }
            //System.out.print(currentVertex.getId() + " ");
            System.out.println(steps == 0? -1: steps);
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SquarePointPair that = (SquarePointPair) o;

            return from == that.from;
        }

        @Override
        public int hashCode() {
            return from;
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
            //vertex.connectedTo.put(this, weight);
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