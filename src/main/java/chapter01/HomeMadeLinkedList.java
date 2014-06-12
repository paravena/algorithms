package chapter01;

import java.util.Iterator;

public class HomeMadeLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;

    private int N;

    private void add(T value) {
        Node tempFirst = new Node(value);
        if (first != null) {
            last.next = tempFirst;
            last = tempFirst;
        } else {
            first = tempFirst;
            last = tempFirst;
        }
        N++;
    }

    @Override
    public Iterator<T> iterator() {
        return new HomeMadeLinkedListIterator();
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private class Node<T> {
        T value;
        Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }


    private class HomeMadeLinkedListIterator implements Iterator<T> {
        Node<T> current;

        private HomeMadeLinkedListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) return null;
            T value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {

        }
    }

    public static void main(String[] args) {
        HomeMadeLinkedList<String> linkedList = new HomeMadeLinkedList<String>();
        linkedList.add("Be");
        linkedList.add("Or");
        linkedList.add("Not");
        linkedList.add("to");
        linkedList.add("Be");
        linkedList.add("That");
        linkedList.add("is");
        linkedList.add("the");
        linkedList.add("question");
        System.out.println("linkedList.size: " + linkedList.size());
        for (String value : linkedList) {
            System.out.println("value = " + value);
        }
    }
}
