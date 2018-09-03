package challenges;

public class MinimumSwaps2 {
    public static int minimumSwaps(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i < arr[i] - 1) {
                swap(i, Math.min(arr.length - 1, arr[i] - 1), arr);
                count++;
                i--;
            }
        }
        return count;

    }

    public static void swap(int i, int j, int[] arr) {
        System.out.println("swapping position " + i + " with " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {7, 1, 3, 2, 4, 5, 6};
        int count = minimumSwaps(arr);
        System.out.println("count = " + count);

        int[] arr1 = new int[] {4, 3, 1, 2};
        int count1 = minimumSwaps(arr1);
        System.out.println("count = " + count1);

        int[] arr2 = new int[] {2, 3, 4, 1, 5};
        int count2 = minimumSwaps(arr2);
        System.out.println("count = " + count2);

        int[] arr3 = new int[] {1, 3, 5, 2, 4, 6, 8};
        int count3 = minimumSwaps(arr3);
        System.out.println("count = " + count3);
    }
}
