package basic;

/**
 * Count the number of occurrences in a sorted array
 * Given a sorted array arr[] and a number x, write a function that counts t
 * he occurrences of x in arr[]. Expected time complexity is O(Logn)
 */
public class CountLogN {
    public static int count(Comparable[] arr, Comparable n) {
        int i = first(arr, 0, arr.length - 1, n);
        System.out.println("i = " + i);
        if (i == -1) return 0;
        int j = last(arr, 0, arr.length - 1, n, arr.length);
        System.out.println("j = " + j);
        return j - i + 1;
    }

    private static int first(Comparable[] arr, int lo, int hi, Comparable element) {
        if (hi < lo) return - 1;
        int mid = lo + (hi - lo) / 2;
        if (element.compareTo(arr[mid]) == 0 && (mid == 0 || element.compareTo(arr[mid - 1]) > 0)) {
            return mid;
        } else if (element.compareTo(arr[mid]) > 0) {
            return first(arr, mid + 1, hi, element);
        } else {
            return first(arr, lo, mid - 1, element);
        }
    }

    private static int last(Comparable[] arr, int lo, int hi, Comparable element, int length) {
        if (hi < lo) return - 1;
        int mid = lo + (hi - lo) / 2;
        if (element.compareTo(arr[mid]) == 0 && (mid == length - 1 || element.compareTo(arr[mid + 1]) < 0)) {
            return mid;
        } else if (element.compareTo(arr[mid]) < 0) {
            return last(arr, lo, mid - 1, element, length);
        } else {
            return last(arr, mid + 1, hi, element, length);
        }
    }



    public static void main(String[] args) {
        Integer[] numbers = {1, 1, 2, 2, 2, 2, 3};
        System.out.println("Number of 2 is " + CountLogN.count(numbers, 2));
        System.out.println("Number of 1 is " + CountLogN.count(numbers, 1));
        System.out.println("Number of 3 is " + CountLogN.count(numbers, 3));

    }



}
