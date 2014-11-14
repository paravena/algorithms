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

    private void buildGraph() {
        wordAdjacencyList = new HashMap<String, Word>();
        for (String word : dictionary) {
            List<String> validWords = findValidWords(word);
            for (String validRelatedWord : validWords) {
                addRelatedWord(word, validRelatedWord);
            }
        }
    }

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

    private Word addWord(String text) {
        Word newWord = new Word(text);
        wordAdjacencyList.put(text, newWord);
        return newWord;
    }

    private Word lookupWord(String text) {
        return wordAdjacencyList.get(text);
    }

    private void addRelatedWord(String wordText, String relatedWordText) {
        Word word = lookupWord(wordText);
        if (word == null) {
            word = addWord(wordText);
        }
        Word relatedWord = lookupWord(relatedWordText);
        if (relatedWord == null) {
            relatedWord = addWord(relatedWordText);
        }
        word.addRelatedWord(relatedWord);
        relatedWord.addRelatedWord(word); // Not sure if this must be symmetric
    }

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

    public void findLongestChains() {
        resetAllWords();
        for (Word word : wordAdjacencyList.values()) {
            findLongestChain(word, true);
        }
    }

    public void findLongestChain(Word initialWord, boolean updateFlg) {
        Stack<Word> stack = new Stack<Word>();
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
        }
        if (lastWord != null && updateFlg) {
            updateMaxDistanceWords(new WordChainEntry(initialWord, lastWord, lastWord.getDistance()));
        }
    }

    private void resetAllWords() {
        for (Word word : wordAdjacencyList.values()) {
            word.setDistance(0);
            word.setVisited(false);
            word.setComingFrom(null);
        }
    }

    public void printLongestChains() {
        while (!maxDistanceWordChainEntriesStack.isEmpty()) {
            WordChainEntry wordChainEntry = maxDistanceWordChainEntriesStack.pop();
            resetAllWords();
            findLongestChain(wordChainEntry.getFromWord(), false);
            traverse(wordChainEntry.getToWord());
        }
    }

    public void traverse(Word word) {
        while (word != null) {
            String sep = (word.getComingFrom() != null) ? " => " : "";
            System.out.print(word.getText() + sep);
            word = word.getComingFrom();
        }
    }

    public static void main(String[] args) {
        WordsChain wordsChain = new WordsChain();
        wordsChain.findLongestChains();
        wordsChain.printLongestChains();
    }

    /**
     * The purpose of this class is to keep the maximum distance for
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
