package heap;

import java.io.InputStream;
import java.util.*;

public class MinimumAverageWaitingTime {
    private static PriorityQueue<PizzaOrder> pizzaOrders;

    public static void main(String[] args) {
        readInput(MinimumAverageWaitingTime.class.getClassLoader().getResourceAsStream("minimum_average_waiting_time.txt"));
        Double average = 0.0;
        int N = pizzaOrders.size();
        PriorityQueue<PizzaOrder> cookingQueue = new PriorityQueue<PizzaOrder>(N, new Comparator<PizzaOrder>() {
            public int compare(PizzaOrder po1, PizzaOrder po2) {
                return po1.getCookingTime().compareTo(po2.getCookingTime());
            }
        });
        PizzaOrder po = pizzaOrders.poll();
        Long clock = po.getArrivalTime();
        po.setCompletionTime(clock + po.getCookingTime());
        clock += po.getCookingTime();
        average += po.getWaitingTime();

        while (pizzaOrders.size() > 0) {
            while (pizzaOrders.size() > 0 && pizzaOrders.peek().getArrivalTime() <= clock) {
                cookingQueue.add(pizzaOrders.poll());
            }
            if (pizzaOrders.size() == 0) {
                break;
            }
            if (cookingQueue.size() > 0) {
                Map<String, Object> result = calculatePartialAverage(cookingQueue, clock);
                average += (Double) result.get("average");
                clock = (Long) result.get("clock");
            }

            if (pizzaOrders.size() == 0) {
                break;
            }

            po = pizzaOrders.poll();
            if (clock <= po.getArrivalTime()) {
                clock = po.getArrivalTime();
            }
            cookingQueue.add(po);
            //po.setCompletionTime(clock + po.getCookingTime());
            //clock += po.getCookingTime();
            //average += po.getWaitingTime();
        }

        if (cookingQueue.size() > 0) {
            average += (Double) calculatePartialAverage(cookingQueue, clock).get("average");
        }

        average = average / N;

        System.out.println(average.longValue()); // 8485548331-80289690037
    }

    private static Map<String, Object> calculatePartialAverage(PriorityQueue<PizzaOrder> cookingQueue, Long clock) {
        Double partialAverage = 0.0;
        while (cookingQueue.size() > 0) {
            PizzaOrder po = cookingQueue.poll();
            po.setCompletionTime(clock + po.getCookingTime());
            clock += po.getCookingTime();
            partialAverage += po.getWaitingTime();
            while (pizzaOrders.size() > 0 && pizzaOrders.peek().getArrivalTime() <= clock) {
                cookingQueue.add(pizzaOrders.poll());
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("average", partialAverage);
        result.put("clock", clock);
        return result;
    }

    public static void readInput() {
        readInput(System.in);
    }

    public static void readInput(InputStream in) {
        Scanner scan = new Scanner(in);
        int N = scan.nextInt();
        pizzaOrders = new PriorityQueue<PizzaOrder>(N, new Comparator<PizzaOrder>() {
            public int compare(PizzaOrder po1, PizzaOrder po2) {
                return po1.getArrivalTime().compareTo(po2.getArrivalTime());
            }
        });
        scan.nextLine();
        for (int i = 0; i < N; i++) {
            String[] tokens = scan.nextLine().split("\\s");
            pizzaOrders.add(new PizzaOrder(Long.valueOf(tokens[0]), Long.valueOf(tokens[1])));
        }
        scan.close();
    }
}

class PizzaOrder {
    private Long arrivalTime;
    private Long cookingTime;
    private Long completionTime;

    public PizzaOrder(Long arrivalTime, Long cookingTime) {
        this.arrivalTime = arrivalTime;
        this.cookingTime = cookingTime;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Long cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Long getWaitingTime() {
        return completionTime - arrivalTime;
    }

    public Long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }
}