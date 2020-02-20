package noahjdk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 描述:
 * 一次在牛客网上线做题的过程，菜鸡
 *
 * @author Noah
 * @create 2020-02-19 22:39
 */
public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            maxLenght(str);

        }
    }

    public static String maxLenght(String str) {

        char[] arr = str.toCharArray();
        Map<String, Integer> map = new HashMap<String, Integer>();
        int maxL = 1;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (map.containsKey(new String("" + c))) {
                int sum = map.get(c);
                sum++;
                if (sum > maxL) {
                    maxL = sum;
                }
                map.put(new String("" + c), sum);
            } else {
                map.put(new String("" + c), 1);
            }
        }

        Set<String> keys = map.keySet();
        String a = null;
        for (String s : keys) {
            int len = map.get(s);
            if (len == maxL) {
                if (a == null) {
                    a = s;
                } else {
                    if (a.toCharArray()[0] > s.toCharArray()[0]) {
                        a = s;
                    }
                }
            }
        }
        for (int i = 0; i < maxL; i++) {
            System.out.print(a);
        }

        return a;
    }
}
