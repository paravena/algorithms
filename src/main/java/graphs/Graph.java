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
}
