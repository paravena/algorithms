package collections;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;


public class CellPhoneGame {
    private static TestCase[] testCases;
    private static String[] answers;

    public static void main(String[] args) throws Exception {
/*
        readAnswersFromFile();

        readInput(CellPhoneGame.class.getClassLoader().getResourceAsStream("input04.txt"));
        int i = 0;
        for (TestCase testCase : testCases) {
            String tc = testCase.toString();
            if (evaluate(testCase)) {
                System.out.println("YES");
                if ("NO".equals(answers[i])) {
                    System.out.println(tc);
                }
            } else {
                System.out.println("NO");
                if ("YES".equals(answers[i])) {
                    System.out.println(tc);
                }
            }
            i++;
        }
*/
        testIndividual();
    }

    public static void testIndividual() {
        TestCase testCase = new TestCase();
        testCase.setArrayLength(1);
        testCase.setJumpLength(1);
        testCase.setArray(new int[]{0});
        boolean value = evaluate(testCase);
        if (value) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void readAnswersFromFile() throws IOException {
        InputStream in = CellPhoneGame.class.getClassLoader().getResourceAsStream("output04.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        int i = 0;
        answers = new String[5000];
        while ((line = br.readLine()) != null) {
            answers[i++] = line;
        }
        br.close();
        in.close();
    }

    private static boolean evaluate(TestCase testCase) {
        int n = testCase.getArrayLength();
        int m = testCase.getJumpLength();
        int[] arr = testCase.getArray();
        int current = 0;
        if (arr[current] == 1) return false;
        if (n <= 1 && arr[0] == 0) return true;

        Stack<Integer> stack = new Stack<Integer>();


        while (current < (n - 1)) {
            if (m <= 1) {
                while(current < (n - 1)) {
                    if (arr[current + 1] == 0) {
                        current++;
                    } else {
                        return false;
                    }
                }
                return true;
            } else  if (current + m > (n - 1)) {
                return true;
            } else if (m > 0 && arr[current + m] == 0) {
                arr[current] = 1;
                if ((current + 1) < (n - 1) && arr[current + 1] == 0) {
                    stack.push(current + 1);
                }
                current += m;
                while (current > 0 && arr[current - 1] == 0) {
                    current--;
                }
                if (current + m > (n - 1)) return true;
            } else if (arr[current + m] == 1 && arr[current + 1] == 1) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    current = stack.pop();
                }
            } else if (arr[current + 1] == 0) {
                current++;
            }
        }
        return false;
    }

    public static void readInput() {
        readInput(System.in);
    }

    public static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int numberOfTestCases = scan.nextInt();
        testCases = new TestCase[numberOfTestCases];
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String firstLine = scan.nextLine();
            String secondLine = scan.nextLine();
            testCases[i] = populateTestCase(firstLine, secondLine);
        }
    }

    private static TestCase populateTestCase(String firstLine, String secondLine) {
        TestCase testCase = new TestCase();
        String[] tokens = firstLine.split("\\s");
        testCase.setArrayLength(Integer.valueOf(tokens[0]));
        testCase.setJumpLength(Integer.valueOf(tokens[1]));
        tokens = secondLine.split("\\s");
        int[] array = new int[testCase.getArrayLength()];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.valueOf(tokens[i]);
        }
        testCase.setArray(array);
        return testCase;
    }
}

class TestCase {
    private int arrayLength;
    private int jumpLength;
    private int[] array;

    public int getArrayLength() {
        return arrayLength;
    }

    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    public int getJumpLength() {
        return jumpLength;
    }

    public void setJumpLength(int jumpLength) {
        this.jumpLength = jumpLength;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        String result = "";
        result += arrayLength + " " + jumpLength + "\n";
        for (int value : array) {
            result += value + ", ";
        }
        return result;
    }
}