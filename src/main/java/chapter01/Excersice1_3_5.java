package chapter01;

// Binary representation of a number
public class Excersice1_3_5 {
    public static void main(String[] args) {
        int N = 50;
        FixedArrayStack<Integer> stack = new FixedArrayStack<Integer>(100);
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }
        for (Integer element : stack) {
            System.out.println("element = " + element);
        }
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }
}
