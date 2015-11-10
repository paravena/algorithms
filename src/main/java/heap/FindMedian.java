package heap;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindMedian {
    public static PriorityQueue<Integer> minHeap;
    public static PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) throws Exception {
        minHeap = new PriorityQueue<Integer>(10, new MinComparator());
        maxHeap = new PriorityQueue<Integer>(10, new MaxComparator());
        readInput(FindMedian.class.getClassLoader().getResourceAsStream("input_find_median.txt"));
    }

    private static float readAnswers() {
        return 0.0f;
    }

    private static void readInput() {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        Scanner answerScan = new Scanner(FindMedian.class.getClassLoader().getResourceAsStream("output_find_median.txt"));
        int N = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < N; i++) {
            String answer = answerScan.nextLine();
            int value = scan.nextInt();
            scan.nextLine();
            addValue(value);
            String result = String.format("%s", calculateMedian());
            System.out.println(result + " " + (!result.equals(answer) ? "false " + answer : "true"));
        }
        System.out.println("maxHeap.size: " + maxHeap.size());
        System.out.println("minHeap.size: " + minHeap.size());
    }

    private static void addValue(int value) {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.add(value);
        } else if (maxHeap.isEmpty() || value > maxHeap.peek()) {
            minHeap.add(value);
        } else if (minHeap.isEmpty() || value < minHeap.peek()) {
            maxHeap.add(value);
        }

        if (maxHeap.size() - minHeap.size() >= 2) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() >= 2) {
            maxHeap.add(minHeap.poll());
        }
    }

    private static float calculateMedian() {
        float result = 0.0f;
        if (maxHeap.size() == minHeap.size()) {
            result = (float) (maxHeap.peek() + minHeap.peek()) / 2;
        } else if (maxHeap.size() > minHeap.size()) {
            result = maxHeap.peek();
        } else {
            result = minHeap.peek();
        }
        return result;
    }
}


class MinComparator implements Comparator<Integer> {
    public int compare(Integer e1, Integer e2) {
        return e1.compareTo(e2);
    }
}

class MaxComparator implements Comparator<Integer> {
    public int compare(Integer e1, Integer e2) {
        return e2.compareTo(e1);
    }
}