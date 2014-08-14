package basic;

public class Excersice1_3_6 {
    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<String>(20);
        Queue<String> queue = new Queue<String>();
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
