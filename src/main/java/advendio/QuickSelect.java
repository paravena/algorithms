package advendio;

/*
    Advendio test
 */

public class QuickSelect {
    public static int findKthLargest(int[] nums, int num) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (nums[pivot] < num) start = pivot + 1;
            else if (nums[pivot] >= num) end = pivot - 1;
            else return pivot;
        }
        return start;
    }

    private static int partition(int[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) start++;
            while (start <= end && nums[end] > nums[pivot]) end--;
            if (start > end) break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        return end;
    }

    public static void main(String[] args) {
        int[] array = {9, 3, 5, 7, 9, 1, 13 };
        int result = findKthLargest(array, 10);
        System.out.println("result 1 = " + result);
        result  = findKthLargest(array, 7);
        System.out.println("result 2 = " + result);
    }
}
