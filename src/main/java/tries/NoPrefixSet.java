package tries;

import java.io.InputStream;
import java.util.Scanner;

public class NoPrefixSet {
    public static void main(String[] args) {
        readInput(NoPrefixSet.class.getClassLoader().getResourceAsStream("no_prefix_set.txt"));
    }

    public static void readInput() {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        TrieDictionary<Boolean> trie = new TrieDictionary<Boolean>();
        Scanner scan = new Scanner(in);
        int N = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < N; i++) {
            String token = scan.nextLine();
            try {
                trie.put(token, true);
            } catch (DuplicatePrefixException e) {
                System.out.println(e.getMessage() + "\n" + e.getKey());
                return;
            }
        }
        System.out.println("GOOD SET");
    }
}

class TrieDictionary<T> {
    private static final int R = 256;
    private TrieNode root;

    private static class TrieNode {
        private TrieNode[] next = new TrieNode[R];
        private Object value;
    }

    public void put(String key, T value) throws DuplicatePrefixException {
        root = put(root, key, value, 0);
    }

    private TrieNode put(TrieNode n, String key, T value, int d) throws DuplicatePrefixException {
        if (n == null) n = new TrieNode();
        if (d == key.length()) {
            if (n.value != null) throw new DuplicatePrefixException("BAD SET", key);
            n.value = value;
            for (int i = 0; i < R; i++) {
                if (n.next[i] != null && n.next[i].value != null) throw new DuplicatePrefixException("BAD SET", key);
            }
            return n;
        }
        char c = key.charAt(d);
        if (n.next[c] != null && n.next[c].value != null) {
            throw new DuplicatePrefixException("BAD SET", key);
        }
        n.next[c] = put(n.next[c], key, value, d + 1);
        return n;
    }
}

class DuplicatePrefixException extends Exception {
    private String key;

    public DuplicatePrefixException() {
        super();
    }

    public DuplicatePrefixException(String message) {
        super(message);
    }

    public DuplicatePrefixException(String message, String key) {
        super(message);
        setKey(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}