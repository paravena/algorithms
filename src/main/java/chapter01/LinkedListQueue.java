package chapter01;

import java.util.Iterator;

public class LinkedListQueue<T> implements Iterable<T> {
    private HomeMadeLinkedList<T> items;

    public LinkedListQueue() {
        items = new HomeMadeLinkedList<T>();
    }

    public void enqueue(T item) {
        items.add(item);
    }

    public T dequeue() {
        return items.removeLast();
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.size() == 0;
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
        LinkedListQueue<String> queue = new LinkedListQueue<String>();
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
