package basic;

public class CircularBinarySearch {
    public static int circularSearch(int[] arr, int key) {
        return circularSearch(arr, key, 0, arr.length - 1);
    }

    public static int circularSearch(int[] arr, int key, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == key) {
            return mid;
        }
        int sortedHalf = 0;
        if (arr[lo] < arr[mid]) {
            sortedHalf = 1;
            if (key >= arr[lo] && key <= arr[mid]) {
                return BinarySearch.search(arr, key, lo, mid);
            }
        } else if (arr[mid] < arr[hi]){
            sortedHalf = 2;
            if (key >= arr[mid] && key <= arr[hi]) {
                return BinarySearch.search(arr, key, mid, hi);
            }
        }

        if (sortedHalf == 1) {
            return circularSearch(arr, key, mid, hi);
        } else {
            return circularSearch(arr, key, lo, mid);
        }
    }

    public static void main(String[] args) {
        int[] arrayRotated = {23, 27, 29, 31, 37, 43, 49, 56, 64, 78, 91, 99, 1, 4, 11, 14, 15, 17, 19};
        int index = CircularBinarySearch.circularSearch(arrayRotated, 14);
        System.out.println("index = " + index);
    }
}
