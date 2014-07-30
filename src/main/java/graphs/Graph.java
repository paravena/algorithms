package graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Graph implements Iterable<Vertex> {
    private Map<String, Vertex> vertList;
    private Integer numVertices;

    public Graph() {
        vertList = new HashMap<String, Vertex>();
        numVertices = 0;
    }

    public Vertex addVertex(String key) {
        numVertices++;
        Vertex newVertex = new Vertex(key);
        vertList.put(key, newVertex);
        return newVertex;
    }

    public void addEdge(String from, String to, Integer cost) {
        if (!vertList.containsKey(from)) addVertex(from);
        if (!vertList.containsKey(to)) addVertex(to);
        vertList.get(from).addNeighbor(to, cost);
    }

    public Collection<Vertex> getVertices() {
        return vertList.values();
    }

    @Override
    public Iterator<Vertex> iterator() {
        return vertList.values().iterator();
    }

    public Integer getNumVertices() {
        return numVertices;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("0");
        g.addVertex("1");
        g.addVertex("2");
        g.addVertex("3");
        g.addVertex("4");
        g.addVertex("5");
        g.addEdge("0", "1", 5);
        g.addEdge("0", "5", 2);
        g.addEdge("1", "2", 4);
        g.addEdge("2", "3", 9);
        g.addEdge("3", "4", 7);
        g.addEdge("3", "5", 3);
        g.addEdge("4", "0", 1);
        g.addEdge("5", "4", 8);
        g.addEdge("5", "2", 1);
        for (Vertex v : g) {
            for (String id : v.getConnections()) {
                System.out.printf("(%s, %s)\n", v.getId(), id);
            }
        }
    }
}
