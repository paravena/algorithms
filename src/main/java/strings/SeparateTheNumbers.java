package strings;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

public class SeparateTheNumbers {
    public boolean isBeautiful(String input) {
        int[] arr = Arrays.stream(input.split("")).mapToInt(Integer::parseInt).toArray();
        int i = 0;
        int width = 1;
        int initialWidth = 1;
        String current;
        try {
            current = getNumberFromArray(i, i + width, arr);
        } catch(Exception e) {
            System.out.println("NO");
            return false;
        }
        String initialValue = current;
        do {
            try {
                i += width;
                if (current.equals(limit(width))) {
                    width++;
                }
                String next = getNumberFromArray(i, i + width, arr);
                if (substract(next, current) == 1) {
                    current = this.getNumberFromArray(i, i + width, arr);
                } else {
                    i = 0;
                    width = ++initialWidth;
                    if (width > arr.length / 2) {
                        System.out.println("NO");
                        return false;
                    }
                    current = this.getNumberFromArray(i, i + width, arr);
                    initialValue = current;
                }
            } catch(Exception e) {
                i = 0;
                width = ++initialWidth;
                if (width > arr.length / 2) {
                    System.out.println("NO");
                    return false;
                }
                try {
                    current = this.getNumberFromArray(i, i + width, arr);
                } catch(Exception e1) {
                    System.out.println("NO");
                    return false;
                }
                initialValue = current;
            }
        } while(i + width < arr.length);
        System.out.println("YES " + initialValue);
        return true;
    }

    private String limit(int n) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            sb.append("9");
        }
        return sb.toString();
    }

    private long substract(String s1, String s2) {
        BigInteger b1 = new BigInteger(s1);
        BigInteger b2 = new BigInteger(s2);
        BigInteger subtract = b1.subtract(b2);
        return Long.valueOf(subtract.toString());
    }

    private String getNumberFromArray(int i, int j, int[] arr) throws Exception {
        int[] temp = Arrays.copyOfRange(arr, i, j);
        if ((j - i) > 1 && temp[0] == 0) {
            throw new Exception("Leading zero");
        }
        return Arrays.toString(temp).replaceAll("(^\\[|\\]$|\\,|\\s)", "");
    }

    public static void main(String[] args) {
        readInput(System.in);
    }

    private static void readInput(InputStream in) {
        SeparateTheNumbers separateTheNumbers = new SeparateTheNumbers();
        // Scanner scan = new Scanner(in);
        Scanner scan = new Scanner(SeparateTheNumbers.class.getClassLoader().getResourceAsStream("separate_the_numbers_input.txt"));
        int numberOfTestCases = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numberOfTestCases; i++) {
            String input = scan.nextLine();
            separateTheNumbers.isBeautiful(input);
        }
    }
}