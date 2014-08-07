package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex {
    private String id;
    private Map<Vertex, Integer> connectedTo;

    public Vertex(String id) {
        this.id = id;
        this.connectedTo = new HashMap<Vertex, Integer>();
    }

    public void addNeighbor(Vertex vertex) {
        addNeighbor(vertex, 0);
    }

    public void addNeighbor(Vertex vertex, Integer weight) {
        connectedTo.put(vertex, weight);
    }

    public Set<Vertex> getConnections() {
        return connectedTo.keySet();
    }

    public String getId() {
        return id;
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
}
