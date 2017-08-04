package implementation;

import java.io.InputStream;
import java.util.*;

import static jdk.nashorn.internal.objects.Global.undefined;

public class GridLandMetro {


    public static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        long n = scan.nextLong();
        long m = scan.nextLong();
        int k = scan.nextInt();
        if (scan.hasNext()) scan.nextLine();
        Map<Long, List<List<Long>>> gridLandMatrix = new HashMap<Long, List<List<Long>>>();
        for (int i = 0; i < k; i++) {
            Long r = scan.nextLong();
            Long c1 = scan.nextLong();
            Long c2 = scan.nextLong();
            if (!gridLandMatrix.containsKey(r)) {
                gridLandMatrix.put(r, new ArrayList<List<Long>>());
            }
            gridLandMatrix.get(r).add(Arrays.asList(c1, c2));
            scan.nextLine();
        }
        System.out.println(numberOfLampPosts(n, m, gridLandMatrix));
    }

    public static Long numberOfLampPosts(Long n, Long m, Map<Long, List<List<Long>>> gridLandMatrix) {
        Long counter = 0L;
        for (Long row : gridLandMatrix.keySet()) {
            if (gridLandMatrix.get(row).size() == 1) {
                List<Long> range = gridLandMatrix.get(row).get(0);
                counter += (range.get(1) - range.get(0)) + 1;
            } else if (gridLandMatrix.get(row).size() > 1) {
                List<List<Long>> ranges = gridLandMatrix.get(row);
                ranges.sort((a, b) -> a.get(0).compareTo(b.get(0)));
                Stack<List<Long>> stack = new Stack<>();
                stack.push(ranges.get(0));
                for (int i = 1; i < ranges.size(); i++) {
                    if (isOverlap(stack.peek(), ranges.get(i))) {
                        List<Long> temp = merge(stack.pop(), ranges.get(i));
                        stack.push(temp);
                    } else {
                        stack.push(ranges.get(i));
                    }
                }

                do {
                    List<Long> range = stack.pop();
                    counter += (range.get(1) - range.get(0)) + 1;
                } while (!stack.empty());
            }
        }
        return (m * n) - counter;
    }

    public static List<Long> merge(List<Long> r1, List<Long> r2) {
        Long c1 = r1.get(0) <= r2.get(0) ? r1.get(0) : r2.get(0);
        Long c2 = r1.get(1) >= r2.get(1) ? r1.get(1) : r2.get(1);
        return Arrays.asList(c1, c2);
    }

    public static boolean isOverlap(List<Long> r1, List<Long> r2) {
        return !(r2.get(0) > r1.get(1) || r2.get(1) < r1.get(0));
    }

    public static void main(String[] args) {
        readInput(GridLandMetro.class.getClassLoader().getResourceAsStream("gridland_metro_input_1.txt"));
    }
}
