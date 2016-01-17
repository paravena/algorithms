package implementation;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BigInteger result = factorial(n);
        System.out.println(result);
    }

    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.valueOf(n);
        for (int i = n - 1; i > 1; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
