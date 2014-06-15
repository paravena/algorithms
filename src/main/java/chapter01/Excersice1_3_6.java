package chapter01;

public class Excersice1_3_6 {
    public static void main(String[] args) {
        FixedArrayStack<String> stack = new FixedArrayStack<String>(20);
        LinkedListBasedQueue<String> queue = new LinkedListBasedQueue<String>();
        queue.enqueue("gato");
        queue.enqueue("perro");
        queue.enqueue("tortuga");
        queue.enqueue("pollo");
        queue.enqueue("pato");

        while (!queue.isEmpty()) {
            stack.push(queue.dequeue());
        }

        System.out.println("Stack data");

        for (String item : stack) {
            System.out.println(item);
        }

        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }

        System.out.println("Queue data");

        for (String item : queue) {
            System.out.println(item);
        }
    }
}
