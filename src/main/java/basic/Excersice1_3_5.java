package basic;

// Binary representation of a number
public class Excersice1_3_5 {
    public static void main(String[] args) {
        int N = 50;
        ArrayStack<Integer> stack = new ArrayStack<Integer>(100);
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }
        for (Integer element : stack) {
            System.out.println("key = " + element);
        }
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }
}
