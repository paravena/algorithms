package challenges.sorting;

import java.util.Arrays;

public class PlayerSorter {

    public static void main(String[] args) {
        Player[] players = {
            new Player("amy", 100),
            new Player("david", 100),
            new Player("heraldo", 50),
            new Player("aakansha", 75),
            new Player("aleksa", 150)
        };

        Arrays.sort(players, new Checker());
        Arrays.stream(players).forEach(System.out::println);
    }
}
