package challenges.queues;

import org.junit.Test;

import java.util.Scanner;

public class QueueUsingTwoStacksTest {
    @Test
    public void shouldProcessFirstTestCase() {
        String[] operations = readTestCaseDataFromFile("queue_using_two_stacks_input_1.txt");
        new QueueUsingTwoStacks().processOperations(operations);
    }

    private String[] readTestCaseDataFromFile(String filePath) {
        Scanner scanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream(filePath));
        int numberOfOperations = scanner.nextInt();
        String[] operations = new String[numberOfOperations];
        scanner.nextLine();
        for (int i = 0; i < numberOfOperations; i++) {
            String line = scanner.nextLine();
            operations[i] = line;
        }
        return operations;
    }
}
