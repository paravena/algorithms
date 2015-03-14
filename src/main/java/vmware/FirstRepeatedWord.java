package vmware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pablo on 3/14/15.
 */
public class FirstRepeatedWord
{

    static String firstRepeatedWord(String s) {
        if (s == null || s.length() == 0) return "";
        Map words = new HashMap<String, Integer>();
        String[] tokens = s.split("[\\s,:;\\-#.]");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            System.out.println(token);
        }
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (words.containsKey(token)) {
                return token;
            } else {
                words.put(token, 0);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        firstRepeatedWord("santo weon soy un:weah");
    }
}
