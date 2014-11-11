package sort;

public class Quick {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        //System.out.printf("%s, %s, %s, %s\n", lo, j, hi, arrayToString(a));
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable v = a[lo];
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

    public static boolean isLess(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static void exchange(Comparable[] a, int i, int j) {

        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static String arrayToString(Comparable[] a) {

        String arrStr = "[";
        for (Comparable element : a) {
            arrStr += element + ",";
        }
        arrStr += "]";
        return arrStr;
    }

    public static void main(String[] args) {
        //Character[] arr = {'S', 'C', 'T', 'X', 'Y', 'R', 'X', 'F', 'T', 'A', 'Z'};
        //Character[] arr = {'Q', 'U', 'I', 'C', 'K', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        Character[] arr = {'K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S'};
        sort(arr);
        System.out.println(arrayToString(arr));
    }
}