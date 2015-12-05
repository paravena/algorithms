import java.util.HashMap;
import java.util.Map;

public class ShortestVacations {
    public static void main(String[] args) {
        ShortestVacations sv = new ShortestVacations();
        int[] A = {7,3,7,3,1,3,4,1};
        int result = sv.solution(A);
        System.out.println("result = " + result);
    }

    public int solution(int[] A) {
        int count = A.length;
        int N = A.length - 1;
        int j = 0;
        int first = A[j];
        Map<Integer, Integer> repetitions = new HashMap<Integer, Integer>(N);
        for (int i = 1; i < A.length; i++) {
            if (A[i] == first) {
                count--;
                first = A[++j];
            }
            if (repetitions.get(A[i]) == null) {
                repetitions.put(A[i], 1);
            } else {
                int previous = repetitions.get(A[i]);
                repetitions.put(A[i], previous + 1);
            }
        }

        int last = A[N];
        for (int i = N; i > 0; i--) {
            if (A[i] == last && repetitions.get(last) > 1) {
                count--;
                last = A[i - 1];
            }
        }
        return count;
    }
}
