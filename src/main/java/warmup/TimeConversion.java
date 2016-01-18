package warmup;

import java.util.Scanner;

public class TimeConversion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String time = in.next();
        System.out.println(converTime(time));
    }

    private static String converTime(String time) {
        String[] tokens = time.split(":");
        int hour = Integer.valueOf(tokens[0]);
        if (time.endsWith("PM")) {
            if (hour > 12) {
                hour += 12;
            }
        } else if (hour == 12 && time.endsWith("AM") ) {
            hour = 0;
        }

        return formatHour(hour) + ":" + tokens[1] + ":" + tokens[2].substring(0, tokens[2].length() - 2);
    }

    private static String formatHour(int hour) {
        if (hour < 10) {
            return "0" + hour;
        }
        return Integer.toString(hour);
    }
}
