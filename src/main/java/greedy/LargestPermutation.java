package greedy;

import java.io.InputStream;
import java.util.*;

public class LargestPermutation {
    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        String[] firstLine = scan.nextLine().split("\\s");
        int N = Integer.parseInt(firstLine[0]);
        int K = Integer.parseInt(firstLine[1]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }
        largestPermutation(arr, K);
    }

    private static void largestPermutation(int[] arr, int K) {
        Map<Integer, List<Integer>> counter = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            if (counter.get(arr[i]) == null) {
                counter.put(arr[i], new LinkedList<Integer>());
            }
            counter.get(arr[i]).add(i);
        }
        int i = 0;
        while (i < K) {

            i++;
        }

    }

}
