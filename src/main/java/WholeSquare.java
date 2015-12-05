public class WholeSquare {
    public int solution(int A, int B) {
        int count = 0;
        for (int i = A; i <= B; i++) {
            double sqrt = Math.sqrt(i);
            if (sqrt == Math.floor(sqrt)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        WholeSquare ws = new WholeSquare();
        int result = ws.solution(4, 17);
        System.out.println("result = " + result);
    }


}
