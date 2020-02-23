package leetcode_interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 描述:
 * 寻找最长连续相同字符：
 * 有一个字符串，找字符串中长度最长的子串，如果有相同长度的子串，则根据ascii码大小选择最小的。
 *
 * @author Noah
 * @create 2020-02-20 09:20
 */
public class _2020_02_20_longestSameString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            getMaxStr(str);
        }
    }

    /**
     * @param s
     * @return
     */
    public static String getMaxStr(String s) {

        Map<Character, Integer> map = new HashMap<>();

        char[] arrs = s.toCharArray();
        int maxLenght = 1;

        for (int i = 0; i < arrs.length; i++) {

            char aa = arrs[i];

            if (map.containsKey(aa)) {

                Integer sum = map.get(aa);
                sum++;
                map.put(aa, sum);

                if (sum > maxLenght) {
                    maxLenght = sum;
                }
            } else {
                map.put(aa, 1);
            }

        }

        Set<Map.Entry<Character, Integer>> its = map.entrySet();
        Character r = null;

        for (Map.Entry<Character, Integer> it : its) {

            if (it.getValue() == maxLenght && r == null) {
                r = it.getKey();
            } else if (it.getValue() == maxLenght) {
                Character tr = it.getKey();
                if (tr < r) {
                    r = tr;
                }
            }
        }

        for (; maxLenght-- > 0; ) {
            System.out.print(r);
        }

        return null;
    }

}
