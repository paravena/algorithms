package chapter02;

public class Merge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (j > hi ) {
                a[k] = aux[i++];
            } else if (i > mid) {
                a[k] = aux[j++];
            } else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void printArray(Object[] arr) {
        System.out.print("[" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.printf(",%s", arr[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        String[] a = {"E", "E", "X", "I", "G", "M", "R", "Z", "A", "C", "E", "R", "T"};
        sort(a);
        printArray(a);
    }
}
