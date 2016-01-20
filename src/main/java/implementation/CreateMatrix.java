package implementation;

public class CreateMatrix {
    public static void main(String[] args) {
        int rows = 101;
        int cols = 80;

        for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 System.out.printf("|%3d, %3d", i, j);
             }
            System.out.println("");
        }
    }
}
