package swordFingerOffer.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述:
 * 第一个只出现一次的字符
 *
 * @author Noah
 * @create 2020-08-28 8:34 上午
 */
public class p50_firstUniqChar {

    public static void main(String[] args) {

        p50_firstUniqChar app = new p50_firstUniqChar();
        System.out.println(app.firstUniqChar("aadadaad"));
    }

    /**
     * noah list和map版本
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {

        if (s == null || s.trim().length() <= 0) {
            return ' ';
        }

        char[] chars = s.toCharArray();

        //不能使用map
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new TreeMap<>();

        for (int i = 0; i <= chars.length - 1; i++) {

            if (!list.contains(chars[i]) && map.get(chars[i]) == null) {
                list.add(chars[i]);
            } else {
                map.put(chars[i], 2);
                list.remove((Object) chars[i]);
            }

        }
        return list != null && list.size() > 0 ? list.get(0) : ' ';
    }
}
