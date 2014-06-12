package chapter01;

public class CircularBinarySearch {
    public static int circularSearch(int[] arr, int key) {

        return circularSearch(arr, key, 0, arr.length - 1);
    }

    public static int circularSearch(int[] arr, int key, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int sortedHalf = 0;
        if (arr[lo] < arr[mid]) {
            BinarySearch.search(arr, key, lo, mid - 1);
            sortedHalf = 1;
        } else if (arr[mid] < arr[hi]){
            BinarySearch.search(arr, key, mid + 1, hi);
            sortedHalf = 2;
        }

        if (sortedHalf == 1) {
            circularSearch(arr, key, lo, mid);
        } else if (sortedHalf == 2) {
            circularSearch(arr, key, mid, hi);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arrayRotated = {23, 27, 29, 31, 37, 43, 49, 56, 64, 78, 91, 99, 1, 4, 11, 14, 15, 17, 19};
        int index = CircularBinarySearch.circularSearch(arrayRotated, 37);
        System.out.println("index = " + index);
    }
}
