package challenges.queues;

import java.util.Arrays;
import java.util.Stack;

public class QueueUsingTwoStacks {
    private final String ENQUEUE = "1";
    private final String DEQUEUE = "2";
    private final String PRINT = "3";

    private Stack<String> firstStack = new Stack<>();
    private Stack<String> secondStack = new Stack<>();

    public void processOperations(String[] lines) {
        Arrays.stream(lines).map(this::parseLine).forEach(operation -> {
            switch (operation[0]) {
                case ENQUEUE:
                    enqueue(operation[1]);
                    break;
                case DEQUEUE:
                    dequeue();
                    break;
                case PRINT:
                    print();
                    break;
            }
        });
    }

    private void enqueue(String value) {
        firstStack.push(value);
    }

    private void dequeue() {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
            secondStack.pop();
        } else {
            secondStack.pop();
        }
    }

    private void print() {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
            System.out.println(secondStack.peek());
        } else if (!secondStack.isEmpty()){
            System.out.println(secondStack.peek());
        }
    }

    private String[] parseLine(String line) {
        return line.split("\\s");
    }
}
