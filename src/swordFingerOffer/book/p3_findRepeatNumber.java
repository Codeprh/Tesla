package swordFingerOffer.book;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 找出数组中重复的数字。
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * @author Noah
 * @create 2020-09-01 6:43 上午
 */
public class p3_findRepeatNumber {

    public static void main(String[] args) {

    }


    /**
     * noah版本：使用map去存储，查找
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {

        Map<Integer, Boolean> map = new HashMap<>();
        for (int a : nums) {
            if (map.containsKey(a)) {
                return a;
            } else {
                map.put(a, true);
            }
        }
        return 0;
    }
}
