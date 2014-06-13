package chapter01;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Excersice1_3_6 {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        Queue<String> queue = new PriorityQueue<String>();
        queue.add("be");
        queue.add("or");
        queue.add("not");
        queue.add("to");
        queue.add("be");
        queue.add("fool");
        for (String element : queue) {
            System.out.println(element);
        }

    }
}
