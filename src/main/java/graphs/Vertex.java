package graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex {
    private String id;
    private Map<Vertex, Integer> connectedTo;
    private Vertex parent;
    private String color;
    private Integer depth;
    private Map<String, Object> data;

    public Vertex(String id) {
        this.id = id;
        color = "white";
        connectedTo = new HashMap<Vertex, Integer>();
        data = new HashMap<String, Object>();
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

    public Object getDataValue(String key) {
        return data.get(key);
    }

    public void setDataValue(String key, Object value) {
        data.put(key, value);
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

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
}
