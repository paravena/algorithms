package challenges;

public class MinimumSwaps2 {
    public static int minimumSwaps(int[] arr) {
        int index = 0;
        int count = 0;
        while ((index = nextIndex(arr, index)) != -1) {
            int position = nextSwapPosition(index + 1, arr);
            if (position != -1) {
                swap(index, position, arr);
                count++;
            }
        }
        return count;

    }

    public static int nextSwapPosition(int from, int[] arr) {
        int prev = arr[from];
        int valueToSwitch = arr[from - 1];
        for (int i = from; i < arr.length; i++) {
            int current = arr[i];
            if (current < prev || i == (valueToSwitch - 1) || i == current) {
                return i;
            }
            prev = current;
        }
        return -1;
    }

    public static int nextIndex(int[] arr, int from) {
        int index = -1;
        int prev = arr[from + 1];
        for (int i = from + 2; i < arr.length; i++) {
            int current = arr[i];
            if (current < prev) {
                index = i;
                break;
            }
            prev = current;
        }
        return index;
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {7, 1, 3, 2, 4, 5, 6};
        System.out.println(nextIndex(arr, 0));
        int[] arr1 = new int[] {2, 1, 3, 7, 4, 5, 6};
        System.out.println(nextIndex(arr1, 0));
//        int[] arr2 = new int[] {1, 2, 3, 7, 4, 5, 6};
//        System.out.println(nextIndex(arr2));
//        int[] arr3 = new int[] {1, 2, 3, 4, 7, 5, 6};
//        System.out.println(nextIndex(arr3));
//        int[] arr4 = new int[] {1, 2, 3, 4, 5, 7, 6};
//        System.out.println(nextIndex(arr4));int[]
//        arr5 = new int[] {1, 2, 3, 4, 5, 6, 7};
//        System.out.println(nextIndex(arr5));


//        int[] arr = new int[] {7, 1, 3, 2, 4, 5, 6};
//        System.out.println(nextSwapPosition(1, arr));
//        int[] arr1 = new int[] {2, 1, 3, 7, 4, 5, 6};
//        System.out.println(nextSwapPosition(1, arr1));
//        int[] arr2 = new int[] {1, 2, 3, 7, 4, 5, 6};
//        System.out.println(nextSwapPosition(4, arr2));
//        int[] arr3 = new int[] {1, 2, 3, 4, 7, 5, 6};
//        System.out.println(nextSwapPosition(5, arr3));


//        int[] arr = new int[] {7, 1, 3, 2, 4, 5, 6};
//        int count = minimumSwaps(arr);
//        System.out.println("count = " + count);
//
//        int[] arr1 = new int[] {4, 3, 1, 2};
//        int count1 = minimumSwaps(arr1);
//        System.out.println("count = " + count1);
//
//        int[] arr2 = new int[] {2, 3, 4, 1, 5};
//        int count2 = minimumSwaps(arr2);
//        System.out.println("count = " + count2);
//
//        int[] arr3 = new int[] {1, 3, 5, 2, 4, 6, 8};
//        int count3 = minimumSwaps(arr3);
//        System.out.println("count = " + count3);
    }
}
