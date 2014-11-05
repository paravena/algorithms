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

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) return;
        if (x.value != null) q.enqueue(pre);
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length() && x.value != null) q.enqueue(pre);
        if (d == pat.length()) return;
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) return length;
        if (x.value != null) length = d;
        if (d == s.length()) return length;
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    public static void main(String[] args) {
        TrieST<String> st = new TrieST<String>();
        st.put("pablo", "aravena");
        st.put("paulina", "allende");
        st.put("pau", "lita");
        st.put("gato", "silvestre");
        st.put("pato", "lucas");

        for (String k : st.keysThatMatch(".ato")) {
            System.out.println("k = " + k);
        }

        String longestPrefix = st.longestPrefixOf("paulina");
        System.out.println("longestPrefix = " + longestPrefix);
    }
}
