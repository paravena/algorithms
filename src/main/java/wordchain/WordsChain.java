package wordchain;

import java.util.*;

public class WordsChain {
    private WordDictionary dictionary;
    // This map represents the adjacency list
    private Map<String, Word> wordAdjacencyList;
    private Stack<WordChainEntry> maxDistanceWordChainEntriesStack;

    public WordsChain() {
        dictionary = WordDictionary.getInstance();
        maxDistanceWordChainEntriesStack = new Stack<WordChainEntry>();
        buildGraph();
    }

    public WordsChain(String dictionaryPath) {
        dictionary = WordDictionary.getInstance(dictionaryPath);
        maxDistanceWordChainEntriesStack = new Stack<WordChainEntry>();
        buildGraph();
    }

    /**
     * Build the related word directed graph
     */
    private void buildGraph() {
        wordAdjacencyList = new HashMap<String, Word>();
        for (String word : dictionary) {
            List<String> validWords = findValidWords(word);
            for (String validRelatedWord : validWords) {
                addRelatedWord(word, validRelatedWord);
            }
        }
    }

    /**
     * Find valid words found in dictionary, related words are the one
     * that differ only in one character
     *
     * @param word word text
     * @return a list of valid related word
     */
    private List<String> findValidWords(String word) {
        List<String> validWords = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            String wordCandidate = word.substring(0, i) + word.substring(i+1);
            if (dictionary.isWord(wordCandidate)) {
                validWords.add(wordCandidate);
            }
        }
        return validWords;
    }

    /**
     * Add a new word in adjacency list
     *
     * @param text word text
     * @return new word
     */
    private Word addWord(String text) {
        Word newWord = lookupWord(text);
        if (newWord == null) {
            newWord = new Word(text);
            wordAdjacencyList.put(text, newWord);
        }
        return newWord;
    }

    /**
     * Look for a word in adjacency list
     *
     * @param text word text
     * @return a word
     */
    private Word lookupWord(String text) {
        return wordAdjacencyList.get(text);
    }

    /**
     * Add a related word in adjacency list
     *
     * @param wordText word
     * @param relatedWordText related word
     */
    private void addRelatedWord(String wordText, String relatedWordText) {
        Word word = addWord(wordText);
        Word relatedWord = addWord(relatedWordText);
        // word.addRelatedWord(relatedWord); // must be directed graph
        relatedWord.addRelatedWord(word);
    }

    /**
     * Keep track of longest distance between two given words after performing the DFS
     *
     * @param wordChainEntry word chain entry
     */
    private void updateMaxDistanceWords(WordChainEntry wordChainEntry) {
        if (maxDistanceWordChainEntriesStack.isEmpty()) {
            maxDistanceWordChainEntriesStack.push(wordChainEntry);
        } else if (wordChainEntry.getDistance() > maxDistanceWordChainEntriesStack.peek().getDistance()) {
            maxDistanceWordChainEntriesStack.removeAllElements();
            maxDistanceWordChainEntriesStack.push(wordChainEntry);
        } else if (wordChainEntry.getDistance() == maxDistanceWordChainEntriesStack.peek().getDistance()) {
            maxDistanceWordChainEntriesStack.push(wordChainEntry);
        }
    }

    /**
     * Determine the longest distance for a list of words
     */
    public void findLongestChains() {
        for (Word word : wordAdjacencyList.values()) {
            List<Word> path = findLongestChain(word, true);
            resetAllWords(path);
        }
    }

    /**
     * Perform a depth first search in order to determine the longest chain
     * reachable for a given word
     *
     * @param initialWord initial word
     * @param updateFlg update longest distance stack
     */
    public List<Word> findLongestChain(Word initialWord, boolean updateFlg) {
        Stack<Word> stack = new Stack<Word>();
        List<Word> path = new ArrayList<Word>();
        initialWord.setDistance(0);
        initialWord.setComingFrom(null);
        Word lastWord = null;
        int maxDistanceReached = 0;
        stack.push(initialWord);
        while (!stack.isEmpty()) {
            Word currentWord = stack.pop();
            for (Word word : currentWord.getRelatedWords()) {
                if (!word.isVisited()) {
                    int nextDistance = currentWord.getDistance() + 1;
                    word.setDistance(nextDistance);
                    word.setComingFrom(currentWord);
                    stack.push(word);
                    if (nextDistance > maxDistanceReached) {
                        lastWord = word;
                        maxDistanceReached = nextDistance;
                    }
                }
            }
            currentWord.setVisited(true);
            path.add(currentWord);
        }
        if (lastWord != null && updateFlg) {
            updateMaxDistanceWords(new WordChainEntry(initialWord, lastWord, lastWord.getDistance()));
        }
        return path;
    }

    /**
     * Reset all words previous to perform a Depth first search
     *  Observation: this method add lot of complexity because is
     *  traversing the complete list of words
     * @param path only the word visited previously during the finding process
     */
    private void resetAllWords(List<Word> path) {
        // for (Word word : wordAdjacencyList.values()) { this line added lot of complexity
        for (Word word : path) {
            word.setDistance(0);
            word.setVisited(false);
            word.setComingFrom(null);
        }
    }

    /**
     * For each longest chain entry print the complete word chain
     */
    public void printLongestChains() {
        List<Word> path = new ArrayList<Word>();
        while (!maxDistanceWordChainEntriesStack.isEmpty()) {
            WordChainEntry wordChainEntry = maxDistanceWordChainEntriesStack.pop();
            resetAllWords(path);
            path = findLongestChain(wordChainEntry.getFromWord(), false);
            traverse(wordChainEntry.getToWord());
        }
    }

    /**
     * Traverse the word chain printing all related words
     *
     * @param word initial word
     */
    private void traverse(Word word) {
        while (word != null) {
            String sep = (word.getComingFrom() != null) ? " => " : "\n";
            System.out.print(word.getText() + sep);
            word = word.getComingFrom();
        }
    }

    public static void main(String[] args) {
        WordsChain wordsChain;
        if (args.length > 0 && args[0].trim().length() > 0) {
            wordsChain = new WordsChain(args[0]);
        } else {
            wordsChain = new WordsChain();
        }
        wordsChain.findLongestChains();
        wordsChain.printLongestChains();
    }

    /**
     * The purpose of this class is to store the maximum distance for
     * a given word from another given word
     */
    class WordChainEntry {
        protected Word fromWord;
        protected Word toWord;
        protected int distance;

        WordChainEntry(Word fromWord, Word toWord, int distance) {
            this.fromWord = fromWord;
            this.toWord = toWord;
            this.distance = distance;
        }

        public Word getFromWord() {
            return fromWord;
        }

        public void setFromWord(Word fromWord) {
            this.fromWord = fromWord;
        }

        public Word getToWord() {
            return toWord;
        }

        public void setToWord(Word toWord) {
            this.toWord = toWord;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
