package chapter01;

import java.util.Iterator;

public class LinkedListBasedQueue<T> implements Iterable<T> {
    private HomeMadeLinkedList<T> items;

    public LinkedListBasedQueue() {
        items = new HomeMadeLinkedList<T>();
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
        return new LinkedListQueueIterator();
    }

    private class LinkedListQueueIterator implements Iterator<T> {
        Iterator<T> iterator;
        private LinkedListQueueIterator() {
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
        LinkedListBasedQueue<String> queue = new LinkedListBasedQueue<String>();
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