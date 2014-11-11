package wordchain;

import java.util.*;

public class WordsChain {
    private WordDictionary dictionary;
    // This map represents the adjacent list
    private Map<String, Word> words;

    public WordsChain() {
        dictionary = WordDictionary.getInstance();
        buildGraph();
    }

    private void buildGraph() {
        words = new HashMap<String, Word>();
        for (String word : dictionary) {
            List<String> validWords = findValidWords(word);
            printList(validWords);
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
        System.out.println("starting at word " + dictionary.getShortestWord());
        Word shortestWord = lookupWord(dictionary.getShortestWord());
        shortestWord.setDistance(0);
        shortestWord.setComingFrom(null);
        shortestWord.setVisited(true);
        findLongestChain(shortestWord, 1, dictionary.size() - 1);
    }

    public void findLongestChain(Word word, int n, int limit) {
        if (n < limit) {
            for (Word rw : word.getRelatedWords()) {
                if (!rw.isVisited()) {
                    rw.setComingFrom(word);
                    rw.setDistance(word.getDistance() + 1);
                    findLongestChain(rw, n + 1, limit);
                }
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

    private void printList(List<String> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            System.out.print(element + (i != (list.size() -1)? "," : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        WordsChain wordsChain = new WordsChain();
        wordsChain.findLongestChain();
        Word word = wordsChain.lookupWord("starting");
        System.out.println(word.getText() + " distance is " + word.getDistance());
    }
}
