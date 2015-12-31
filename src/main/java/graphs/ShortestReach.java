package graphs;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class ShortestReach {
    public static void main(String[] args) {
        ShortestReach sr = new ShortestReach();
        sr.readInput(ShortestReach.class.getClassLoader().getResourceAsStream("shortest_path.txt"));
    }

    private void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int T = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < T; i++) {
            TestCase testCase = readTestCase(scan);
            testCase.shortestPath();
            testCase.printResult();
        }
    }

    private TestCase readTestCase(Scanner scan) {
        String line = scan.nextLine();
        String[] tokens = line.split("\\s");
        int N = Integer.valueOf(tokens[0]);
        int M = Integer.valueOf(tokens[1]);
        TestCase testCase = new TestCase(N);
        for (int i = 1; i <= N; i++) {
            testCase.addVertex(new Vertex(i, Integer.MAX_VALUE));
        }

        for (int i = 0; i < M; i++) {
            String[] edges = scan.nextLine().split("\\s");
            int fromId = Integer.valueOf(edges[0]);
            int toId = Integer.valueOf(edges[1]);
            testCase.addEdge(fromId, toId);
        }
        int startId = scan.nextInt();
        testCase.setStart(startId);
        if (scan.hasNext()) scan.nextLine();
        return testCase;
    }

    private class TestCase {
        private Map<Integer, Vertex> vertexList;
        private Vertex startVertex;
        private Integer numberOfVertex;

        public TestCase(Integer numberOfVertex) {
            this.numberOfVertex = numberOfVertex;
            vertexList = new HashMap<Integer, Vertex>();
        }

        public void addVertex(Vertex vertex) {
            vertexList.put(vertex.getId(), vertex);
        }

        public void addEdge(Integer fromId, Integer toId) {
            Vertex fromVertex = vertexList.get(fromId);
            Vertex toVertex = vertexList.get(toId);
            fromVertex.addNeighbor(toVertex, 6);
        }

        public void setStart(Integer id) {
            startVertex = vertexList.get(id);
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

        private void printResult() {
            for (int i = 1; i <= numberOfVertex; i++) {
                if (i != startVertex.getId()) {
                    Vertex vertex = vertexList.get(i);
                    System.out.printf("%s ",  vertex.getDistance() == Integer.MAX_VALUE ? -1 : vertex.getDistance());
                }
            }
            System.out.print("\n");
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
