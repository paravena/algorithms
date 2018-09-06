package challenges.sorting;

import java.util.Comparator;

public class Checker implements Comparator<Player> {
    // complete this method
    public int compare(Player a, Player b) {
        if (b.getScore() != a.getScore()) {
            return b.getScore() - a.getScore();
        } else {
            return b.getName().compareTo(a.getName());
        }
    }
}
