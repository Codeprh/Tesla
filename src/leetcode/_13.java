package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字
 *
 * @author codingprh
 * @create 2019-07-14 09:49
 */
public class _13 {
    /**
     * @param s
     * @return
     */
    public int romanToInt(String s) {

        Map<String, Integer> numMap = letter2Number();

        char[] arrs = s.toCharArray();
        int len = arrs.length;
        int sum = 0;

        for (int i = 0; i < len; ) {

            int temp = i + 1;

            if (temp < len && numMap.containsKey("" + arrs[i] + arrs[temp])) {
                sum += numMap.get("" + arrs[i] + arrs[temp]);
                i += 2;
            } else {
                sum += numMap.get("" + arrs[i]);
                i++;
            }
        }
        return sum;
    }

    /**
     * //I             1
     * //V             5
     * //X             10
     * //L             50
     * //C             100
     * //D             500
     * //M             1000
     * <p>
     * // I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * // X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * // C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * <p>
     * 把所有可能的情况列出来
     *
     * @return
     */
    private Map<String, Integer> letter2Number() {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        return map;
    }

    public static void main(String[] args) {
        _13 app = new _13();
        System.out.println(app.romanToInt("III"));
    }
}
