package wordchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordDictionary implements Iterable<String> {
    private static WordDictionary instance;
    private Map<String, Integer> words;

    public static WordDictionary getInstance() {
        if (instance == null) {
            instance = new WordDictionary();
        }
        return instance;
    }

    private WordDictionary() {
        try {
            loadDictionary();
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    }

    public boolean isWord(String word) {
        return words.containsKey(word);
    }

    private void loadDictionary() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("words-chain.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        words = new HashMap<String, Integer>();
        while ((line = br.readLine()) != null) {
            if (!words.containsKey(line)) {
                words.put(line, 0);
            } else {
                words.put(line, words.get(line) + 1);
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary.getInstance();
    }

    @Override
    public Iterator<String> iterator() {
        return words.keySet().iterator();
    }
}
