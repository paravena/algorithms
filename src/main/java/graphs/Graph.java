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

    public void addVertex(String id) {
        addVertex(new Vertex(id));
    }

    public void addVertex(Vertex vertex) {
        numVertices++;
        vertList.put(vertex.getId(), vertex);
    }

    public Vertex getVertexById(String id) {
        return vertList.get(id);
    }

    public void addEdge(String fromId, String toId) {
        addEdge(fromId, toId, 0);
    }

    public void addEdge(String fromId, String toId, Integer weight) {
        Vertex from;
        Vertex to;
        if (!vertList.containsKey(fromId)) {
            addVertex(fromId);
        }
        if (!vertList.containsKey(toId)) {
            addVertex(toId);
        }
        from = getVertexById(fromId);
        to = getVertexById(toId);
        addEdge(from, to, weight);
    }

    public void addEdge(Vertex from, Vertex to, Integer weight) {
        vertList.get(from.getId()).addNeighbor(to, weight);
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
            for (Vertex n : v.getConnections()) {
                System.out.printf("(%s, %s)\n", v.getId(), n.getId());
            }
        }
    }
}
