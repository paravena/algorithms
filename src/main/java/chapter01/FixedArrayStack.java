package chapter01;

import java.util.Iterator;

public class FixedArrayStack<T> implements Iterable<T> {
    private T[] items;
    private int N;

    public FixedArrayStack() {
        this(10);
    }

    public FixedArrayStack(int initialCapacity) {
        items = (T[]) new Object[initialCapacity];
    }

    public void push(T item) {
        // one quarter before fulling the array
        if (N == items.length) {
            resize(items.length * 2);
        }
        items[N++] = item;
    }

    public T pop() {
        T item = items[--N];
        items[N] = null;
        if (N > 0 && N == items.length / 4) {
            System.out.println("resizing to " + items.length / 2);
            resize(items.length / 2);
        }
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int capacity() {
        return items.length;
    }

    private void resize(int newCapacity) {
        T[] temp = (T[]) new Object[newCapacity];
        for (int i = 0; i < N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new FixedArrayStackIterator();
    }

    private class FixedArrayStackIterator implements Iterator<T> {
        private int i = N;
        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public T next() {
            return items[i--];
        }

        @Override
        public void remove() {
            // No implementation here
        }
    }

    public static void main(String[] args) {
        FixedArrayStack<String> stack = new FixedArrayStack<String>(10);
        stack.push("Be");
        stack.push("or");
        stack.push("not");
        stack.push("to");
        stack.push("be");
        stack.push("about");
        stack.push("to");
        System.out.println("stack.capacity : " + stack.capacity());
        stack.push("get");
        stack.push("the");
        stack.push("array");
        System.out.println("stack.capacity : " + stack.capacity());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println("stack.capacity : " + stack.capacity());

        stack.push("full");
        for (String item : stack) {
            System.out.println("item = " + item);
        }
    }
}
