package wordchain;

import java.util.*;

public class WordsChain {
    private WordDictionary dictionary;
    // This map represents the adjacency list
    private Map<String, Word> wordAdjacencyList;
    private Stack<Word> maxDistanceWordsStack;

    public WordsChain() {
        dictionary = WordDictionary.getInstance();
        maxDistanceWordsStack = new Stack<Word>();
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

    private void updateMaxDistanceWords(Word word) {
        if (maxDistanceWordsStack.isEmpty()) {
            maxDistanceWordsStack.push(word);
        } else {
            if (word.getDistance() > maxDistanceWordsStack.peek().getDistance()) {
                maxDistanceWordsStack.removeAllElements();
                maxDistanceWordsStack.push(word);
            } else if (word.getDistance() == maxDistanceWordsStack.peek().getDistance()) {
                maxDistanceWordsStack.push(word);
            }
        }
    }

    public void findLongestChain() {
        resetAllWords();
        for (Word word : wordAdjacencyList.values()) {
            findLongestChain(word);
        }
    }

    public void findLongestChain(Word initialWord) {
        Stack<Word> stack = new Stack<Word>();
        initialWord.setDistance(0);
        initialWord.setComingFrom(null);
        stack.push(initialWord);
        while (!stack.isEmpty()) {
            Word currentWord = stack.pop();
            for (Word word : currentWord.getRelatedWords()) {
                if (!word.isVisited()) {
                    word.setDistance(currentWord.getDistance() + 1);
                    word.setComingFrom(currentWord);
                    stack.push(word);
                }
            }
            currentWord.setVisited(true);
        }
    }

    private void resetAllWords() {
        for (Word word : wordAdjacencyList.values()) {
            word.setDistance(0);
            word.setVisited(false);
            word.setComingFrom(null);
        }
    }

    public void traverse(String wordText) {
        Word word = lookupWord(wordText);
        while (word != null) {
            String sep = (word.getComingFrom() != null) ? " => " : "";
            System.out.print(word.getText() + sep);
            word = word.getComingFrom();
        }
    }

    public static void main(String[] args) {
        WordsChain wordsChain = new WordsChain();
        wordsChain.findLongestChain(wordsChain.lookupWord("statin"));
        wordsChain.traverse("starting");
    }

    /**
     * The purpose of this class is to keep the maximum distance for
     * a given word from another given word
     */
    class WordChainEntry {
        protected Word fromWordText;
        protected Word toWordText;
        protected int distance;

        public Word getFromWordText() {
            return fromWordText;
        }

        public void setFromWordText(Word fromWordText) {
            this.fromWordText = fromWordText;
        }

        public Word getToWordText() {
            return toWordText;
        }

        public void setToWordText(Word toWordText) {
            this.toWordText = toWordText;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
