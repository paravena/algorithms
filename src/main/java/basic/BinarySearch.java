package basic;

public class BinarySearch {
    public static int search(int[] arr, int key) {
        return search(arr, key, 0, arr.length - 1);
    }

    public static int search(int[] arr, int key, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < arr[mid]) {
            return search(arr, key, lo, mid - 1);
        } else if (key > arr[mid]) {
            return search(arr, key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1 , 2, 3, 4, 5, 6, 14, 28, 49, 100};
        int index = BinarySearch.search(arr, 28);
        System.out.println("index = " + index);
        index = BinarySearch.search(arr, 23);
        System.out.println("index = " + index);
        index = BinarySearch.search(arr, 100);
        System.out.println("index = " + index);
    }
}
