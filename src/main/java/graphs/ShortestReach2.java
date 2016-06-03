package graphs;

import java.io.InputStream;
import java.util.Scanner;

public class ShortestReach2 {
    public static void main(String[] args) {
        ShortestReach2 sr = new ShortestReach2();
        sr.readInput(System.in);
    }

    private void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            TestCase tc = readTestCase(scan);
        }
    }

    private TestCase readTestCase(Scanner scan) {
        return null;
    }

    private class TestCase {

    }
}
