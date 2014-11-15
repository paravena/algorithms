package wordchain;

import java.util.HashSet;
import java.util.Set;

public class Word {
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

    public void addRelatedWord(Word word) {
        relatedWords.add(word);
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

    @Override
    public String toString() {
        return "Word{" +
                "text='" + text + '\'' +
                '}';
    }
}
