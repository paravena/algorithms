package sort;

import java.util.Comparator;

public class Quick<T extends Comparable<T>> {
    private static Quick instance;
    private Comparator<T> comparator;

    private Quick(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public static Quick getInstance() {
        return getInstance(new DefaultComparator());
    }

    public static Quick getInstance(Comparator comparator) {
        if (instance == null) {
            instance = new Quick(comparator);
        }
        return instance;
    }

    public void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(T[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private int partition(T[] a, int lo, int hi) {
        T v = a[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (isLess(a[++i], v)) if (i == hi) break;
            while (isLess(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private boolean isLess(T a, T b) {
        return comparator.compare(a, b) < 0;
    }

    private void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static String arrayToString(Comparable[] a) {
        String arrStr = "[";
        for (Comparable element : a) {
            arrStr += element + ",";
        }
        arrStr += "]";
        return arrStr;
    }

    static class DefaultComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        //Character[] arr = {'S', 'C', 'T', 'X', 'Y', 'R', 'X', 'F', 'T', 'A', 'Z'};
        //Character[] arr = {'Q', 'U', 'I', 'C', 'K', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        /*
        Character[] arr = {'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S'};
        Quick<Character> quick = Quick.getInstance();
        quick.sort(arr);
        System.out.println(arrayToString(arr));
        */
        String[] words = {"a", "at", "bat", "be", "bee", "sat", "sati", "satin", "starting", "statin", "stating"};
        Quick<String> quickStringLength = Quick.getInstance(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() > s2.length()) return 1;
                else if (s1.length() < s2.length()) return -1;
                return 0;
            }
        });
        quickStringLength.sort(words);
        System.out.println(arrayToString(words));

    }
}