package leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author codingprh
 * @create 2019-07-10 09:19
 */
public class _169 {
    /**
     * hash来解决求众数
     *
     * @param nums
     * @return
     */
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

    /**
     * Boyer-Moore 投票算法
     *
     * @param nums
     * @return
     */
    public int majorityElement_v2(int[] nums) {
        int result = 0;
        int count = 0;
        for (int num : nums) {

            if (count == 0) {
                result = num;
            }

            if (result != num) {
                count--;
            } else {
                count++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _169 app = new _169();
        System.out.println(app.majorityElement_v2(new int[]{7, 7, 5, 7, 5, 1, 5, 7, 5, 5, 7, 7, 7, 7, 7, 7}));

    }
}
