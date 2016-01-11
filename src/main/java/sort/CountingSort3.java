    package sort;

    import java.io.InputStream;
    import java.util.Scanner;

    public class CountingSort3 {
        private static final int LIMIT = 100;
        public static void main(String[] args) {
            readInput(System.in);
        }

        private static void readInput(InputStream in) {
            Scanner scan = new Scanner(in);
            int size = scan.nextInt();
            scan.nextLine();
            int[] arr = new int[LIMIT];
            int i = 0;
            while (i < size) {
                String[] tokens = scan.nextLine().split("\\s");
                int number = Integer.valueOf(tokens[0]);
                arr[number]++;
                i++;
            }
            printArray(arr);
        }

        private static void printArray(int[] arr) {
            int count = 0;
            for (int i = 0; i < LIMIT; i++) {
                count += arr[i];
                System.out.printf("%d ", count);
            }
            System.out.println("");
        }
    }
