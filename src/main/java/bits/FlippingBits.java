package bits;

import java.io.InputStream;
import java.util.Scanner;

public class FlippingBits {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        readInput(System.in);
    }

    public static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int T = scan.nextInt();
        scan.nextLine();
        for (int i =0; i < T;i++){
            long n = scan.nextLong();
            flip(n);
            //scan.nextLine();
        }
    }

    public static void flip(long     num) {
        long maxnum = (long)Math.pow(2,32) - 1;
        long r = num ^ maxnum;
        System.out.println(r);

    }
}


