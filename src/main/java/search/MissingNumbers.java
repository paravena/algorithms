package search;

import java.io.InputStream;
import java.util.*;

public class MissingNumbers {
    public static final int LENGTH = 101;
    private Data[] numbers;
    public static void main(String[] args) {
        MissingNumbers mn = new MissingNumbers();
        mn.readInput(mn.getClass().getClassLoader().getResourceAsStream("missing_numbers_input.txt"));
    }

    public MissingNumbers() {
        numbers = new Data[LENGTH];
    }

    private void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        scan.nextLine();
        readArray(scan, false);
        scan.nextLine();
        readArray(scan, true);
        printResult(numbers);
    }

    private void printResult(Data[] numbers) {
        List<Integer> result = new ArrayList<Integer>();
        int i = 0;
        for (Data number : numbers) {
            if (number != null && number.occurrences > 0) {
                result.add(number.originalNum);
            }
        }

        Collections.sort(result);

        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println("");
    }

    private void readArray(Scanner scan, boolean incrementFlg) {
        String[] tokens = scan.nextLine().split("\\s");
        int incrementVal = incrementFlg ?  1 : -1;
        for (String token : tokens) {
            int number = Integer.valueOf(token);
            int idx = number % LENGTH;
            if (numbers[idx] == null) {
                numbers[idx] = new Data(number, incrementVal);
            } else {
                numbers[idx].occurrences += incrementVal;
            }
        }
    }
}

class Data {
    public Data(int originalNum, int occurrences) {
        this.originalNum = originalNum;
        this.occurrences = occurrences;
    }
    int originalNum;
    int occurrences;
}