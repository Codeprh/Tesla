package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author codingprh
 * @create 2019-07-10 09:19
 */
public class _169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int countTemp = 0;
        int result = 0;
        for (Integer num : nums) {
            map.merge(num, 1, (oldVal, newVal) -> {
                return oldVal + newVal;
            });
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> z = iterator.next();
            int count = z.getValue();
            if (count > countTemp) {
                countTemp = count;
                result = z.getKey();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        _169 app = new _169();
        System.out.println(app.majorityElement(new int[]{2,2,1,1,1,2,2}));

    }
}
