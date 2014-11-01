package graphs;


import basic.Queue;

import java.io.*;
import java.util.*;

/**
 * This class illustrates the usage of the Breadth First Search technique applied on graphs
 */
public class WordLadder {
    public Graph buildGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("words.txt")));
        String word;
        Map<String, List<String>> d = new HashMap<String, List<String>>();
        Graph g = new Graph();
        while ((word = br.readLine()) != null) {
            for (int i = 0; i < word.length(); i++) {
                String bucket = word.substring(0, i) + "_" + word.substring(i + 1);
                if (d.containsKey(bucket)) {
                    d.get(bucket).add(word);
                } else {
                    d.put(bucket, new ArrayList<String>());
                    d.get(bucket).add(word);
                }
            }
            for (String bucket : d.keySet()) {
                for (String word1 : d.get(bucket)) {
                    for (String word2 : d.get(bucket)) {
                        if (!word1.equals(word2)) {
                            g.addEdge(word1, word2);
                        }
                    }
                }
            }
        }
        return g;
    }

    public void traverse(Vertex from) {
        while (from.getParent() != null) {
            System.out.println(from.getId());
            from = from.getParent();
        }
        System.out.println(from.getId());
    }

    public static void main(String[] args) throws IOException {
        WordLadder bfs = new WordLadder();
        Graph graph = bfs.buildGraph();
        graph.bfs(graph.getVertexById("fool"));
        Vertex sage = graph.getVertexById("sage");
        bfs.traverse(sage);
    }
}
