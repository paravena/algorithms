package toptal;

import java.util.ArrayList;
import java.util.List;

public class NegativeBaseConverter {

    public static void main(String[] args) {
        NegativeBaseConverter negativeBaseConverter = new NegativeBaseConverter();
        List<Integer> integers = negativeBaseConverter.fromNumberToArray(-9);
        for (int n : integers) {
            System.out.println("n = " + n);
        }
    }

    private List<Integer> fromNumberToArray(int num) {
        int base = -2;
        List<Integer> result = new ArrayList<>();
        while (num != 0) {
            int rest = num % base;
            num = num / base;
            if (rest < 0) {
                num += 1;
                rest += 2;
            }
            result.add(Math.abs(rest));
        }
        return result;
    }
}
