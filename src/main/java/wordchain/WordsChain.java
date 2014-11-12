package wordchain;

import java.util.*;

public class WordsChain {
    private WordDictionary dictionary;
    // This map represents the adjacent list
    private Map<String, Word> words;
    private String startWord;
    private String furthestWord;
    private int maxDistance;

    public WordsChain() {
        dictionary = WordDictionary.getInstance();
        startWord = dictionary.getShortestWord();
        buildGraph();
    }

    private void buildGraph() {
        words = new HashMap<String, Word>();
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

    public void findLongestChain() {
        Word shortestWord = lookupWord(getStartWord());
        shortestWord.setDistance(0);
        shortestWord.setComingFrom(null);
        shortestWord.setVisited(true);
        findLongestChain(shortestWord, 1);
    }

    public void findLongestChain(Word word, int n) {
        int currentDistance = word.getDistance();
        for (Word rw : word.getRelatedWords()) {
            if (!rw.isVisited()) {
                rw.setComingFrom(word);
                updateMaxDistance(currentDistance + 1, rw);
                rw.setDistance(currentDistance + 1);
                rw.setVisited(true);
                findLongestChain(rw, ++n);
            }
        }
    }

    private Word addWord(String text) {
        Word newWord = new Word(text);
        words.put(text, newWord);
        return newWord;
    }

    private Word lookupWord(String text) {
        return words.get(text);
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

    public void updateMaxDistance(int distance, Word word) {
        if (distance > maxDistance) {
            maxDistance = distance;
            furthestWord = word.getText();
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

    public String getStartWord() {
        return startWord;
    }

    public String getFurthestWord() {
        return furthestWord;
    }


    public static void main(String[] args) {
        WordsChain wordsChain = new WordsChain();
        wordsChain.findLongestChain();
        wordsChain.traverse(wordsChain.getFurthestWord());
    }
}
