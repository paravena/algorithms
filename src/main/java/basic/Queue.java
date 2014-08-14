package basic;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private LinkedList<T> items;

    public Queue() {
        items = new LinkedList<T>();
    }

    public void enqueue(T item) {
        items.add(item);
    }

    public T dequeue() {
        return items.removeFirst();
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }

    public T last() {
        return items.last();
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
        Iterator<T> iterator;
        private QueueIterator() {
            iterator = items.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }

        @Override
        public void remove() {
            // No implementation here
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        queue.enqueue("Be");
        queue.enqueue("or");
        queue.enqueue("not");
        queue.enqueue("to");
        queue.enqueue("be");
        queue.enqueue("about");
        queue.enqueue("to");
        queue.enqueue("get");
        queue.enqueue("the");
        queue.enqueue("array");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.enqueue("fool");

        for (String item : queue) {
            System.out.println("item = " + item);
        }
    }
}
