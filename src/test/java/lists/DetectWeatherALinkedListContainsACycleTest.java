package lists;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import challenges.lists.DetectWeatherALinkedListContainsACycle;
import org.junit.Test;

import challenges.lists.SinglyLinkedList;

import static junit.framework.TestCase.assertFalse;

public class DetectWeatherALinkedListContainsACycleTest {
    @Test
    public void shouldPassTestCase1() {
        List<SinglyLinkedList> result = readFromTestDataFromFile("detect_weather_a_linked_list_contains_a_cycle_input_1.txt");
        result.stream().forEach(list -> {
            assertFalse(DetectWeatherALinkedListContainsACycle.hasCycle(list.head));
        });
    }

    @Test
    public void shouldPassTestCase2() {
        List<SinglyLinkedList> result = readFromTestDataFromFile("detect_weather_a_linked_list_contains_a_cycle_input_2.txt");
        result.stream().forEach(list -> {
            assertFalse(DetectWeatherALinkedListContainsACycle.hasCycle(list.head));
        });
    }

    private List<SinglyLinkedList> readFromTestDataFromFile(String testDataFilePath) {
        List<SinglyLinkedList> result = new ArrayList<>();
        Scanner scanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream(testDataFilePath));

        int tests = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int testsItr = 0; testsItr < tests; testsItr++) {
            int index = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            SinglyLinkedList llist = new SinglyLinkedList();
            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                llist.insertNode(llistItem);
            }
            result.add(llist);
        }
        return result;
    }
}
