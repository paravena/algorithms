package chapter01;

public class Excersice1_1_14 {
    public static double lg10(double n) {
        int i = 0;
        while (n > 10) {
            n /= 10;
            ++i;
        }

        double m = Math.pow(n, 10); // mantise
        double mm = 0.0;
        for (int j = 0; j < 4; j++) {
            int d = 0;
            while (m > 10) {
                m /= 10;
                ++d;
            }
            mm += d * Math.pow(10, 4 - j);
            m = Math.pow(m, 10);
        }
        return (i * 100000 + mm) / 100000;
    }


    public static void main(String[] args) {
        System.out.println("lg10 = " + lg10(1235));
    }
}
