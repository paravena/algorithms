package test;

public class InternStringTest {
public static synchronized void test () {

}
    public static void main(String[] args) {
        String a = "foo";
        String b = "food".substring(0, 3);
        System.out.println("b = " + b);
        String c = b.intern();

        if (a.equals(b)) {
            if (a == b) {
                System.out.println("1");
            } else if (a == c) {
                System.out.println("2");
            } else {
                System.out.println("3");
            }
        } else {
            System.out.println("4");
        }
    }
}
