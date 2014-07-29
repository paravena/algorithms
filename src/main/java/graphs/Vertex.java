package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex {
    private String id;
    private Map<String, Integer> connectedTo;

    public Vertex(String id) {
        this.id = id;
        this.connectedTo = new HashMap<String, Integer>();
    }

    public void addNeighbor(String nbr) {
        addNeighbor(nbr, 0);
    }

    public void addNeighbor(String nbr, Integer weight) {
        connectedTo.put(nbr, weight);
    }

    public Set<String> getConnections() {
        return connectedTo.keySet();
    }

    public String getId() {
        return id;
    }

    public Integer getWeight(String nbr) {
        return connectedTo.get(nbr);
    }
}
