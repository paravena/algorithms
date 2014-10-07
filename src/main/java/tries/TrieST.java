package tries;

import basic.Queue;

public class TrieST<T> {
    private static final int R = 256;
    private Node root;

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    public T get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (T) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, T value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, T value, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.value = value;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d+1);
        return x;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.value != null) q.enqueue(pre);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    public static void main(String[] args) {
        TrieST<String> st = new TrieST<String>();
        st.put("pablo", "aravena");
        st.put("paulina", "allende");
        for (String k : st.keys()) {
            System.out.println("k = " + k);
        }
    }
}
