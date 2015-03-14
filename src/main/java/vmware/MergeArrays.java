package vmware;

public class MergeArrays {
    public static void mergeArray(int[] a, int[] b, int M) {
        if (a[0] < b[0]) {
            System.arraycopy(b, 0, b, M, M);
        }

        System.arraycopy(a, 0, b, 0, M);
    }

    public static void main(String[] args) {
        int[] b = new int[]{10, 20, 30, 0,0,0};
        mergeArray(new int[] {1, 2, 4}, b , 3);
        for (int i = 0; i < 6; i++) {
            int i1 = b[i];
            System.out.println(i1);
        }
    }
}
