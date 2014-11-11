package wordchain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordsChain {
    private WordDictionary dictionary;
    public WordsChain() {
        dictionary = WordDictionary.getInstance();
        buildGraph();
    }

    private void buildGraph() {
        for (String word : dictionary) {
            System.out.print("Valid words for " + word + " are: ");
            List<String> validWords = findValidWords(word);
            printList(validWords);
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

    private void printList(List<String> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            System.out.print(element + (i != (list.size() -1)? "," : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        new WordsChain();
    }

    class Word {
        protected String text;
        protected Set<Word> relatedWords = new HashSet<Word>();
        protected boolean visited;
        protected Word comingFrom;
        protected int distance;

        public Word(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Set<Word> getRelatedWords() {
            return relatedWords;
        }

        public void setRelatedWords(Set<Word> relatedWords) {
            this.relatedWords = relatedWords;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public Word getComingFrom() {
            return comingFrom;
        }

        public void setComingFrom(Word comingFrom) {
            this.comingFrom = comingFrom;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word = (Word) o;
            return !(text != null ? !text.equals(word.text) : word.text != null);
        }

        @Override
        public int hashCode() {
            return text != null ? text.hashCode() : 0;
        }
    }
}
