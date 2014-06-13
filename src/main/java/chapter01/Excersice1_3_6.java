package chapter01;

public class Excersice1_3_6 {
    public static void main(String[] args) {
        FixedArrayStack<String> stack = new FixedArrayStack<String>(20);
        LinkedListQueue<String> queue = new LinkedListQueue<String>();
        queue.enqueue("be");
        queue.enqueue("or");
        queue.enqueue("not");
        queue.enqueue("to");
        queue.enqueue("be");
        queue.enqueue("fool");

        for (String item : queue) {
            System.out.println(item);
        }

        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }

        for (String item : stack) {
            System.out.println(item);
        }

        /*
        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }

        for (String item : queue) {
            System.out.println(item);
        }
        */
    }
}
