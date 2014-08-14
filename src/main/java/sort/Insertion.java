package sort;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            int j = i;
            while(j >= 1 && a[j].compareTo(a[j-1]) < 0) {
                exchange(a, j - 1, j);
                j--;
            }
        }
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void printArray(Object[] arr) {
        System.out.print("[" + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.printf(",%s", arr[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        printArray(a);
        sort(a);
        printArray(a);

    }
}
