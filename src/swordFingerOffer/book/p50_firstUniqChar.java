package swordFingerOffer.book;

import java.util.*;

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

    /**
     * 只使用一个map
     *
     * @param s
     * @return
     */
    public char firstUniqChar_right(String s) {

        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();

        //把结果存储到map中
        for (char c : sc) {
            dic.put(c, !dic.containsKey(c));
        }

        //再次遍历元数组，等到第一个出现数字
        for (char c : sc) {
            if (dic.get(c)) return c;
        }

        return ' ';
    }

    /**
     * 有序哈希表:按照插入顺序排序
     *
     * @param s
     * @return
     */
    public char firstUniqChar_right2(String s) {

        Map<Character, Boolean> dic = new LinkedHashMap<>();
        char[] sc = s.toCharArray();

        for (char c : sc) {
            dic.put(c, !dic.containsKey(c));
        }

        for (Map.Entry<Character, Boolean> d : dic.entrySet()) {
            if (d.getValue()) {
                return d.getKey();
            }
        }
        return ' ';
    }


}
