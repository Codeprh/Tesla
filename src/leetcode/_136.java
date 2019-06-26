package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author codingprh
 * @create 2019-06-26 13:56
 */
public class _136 {
    /**
     * 使用hash表解决
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, (old, n) -> old + 1);
        }
        Optional<Integer> result = map.entrySet().stream().filter(mapp -> mapp.getValue().equals(1)).map(mapp -> mapp.getKey()).findFirst();

        return result.get();
    }

    /**
     * 高端解法：异或操作
     *
     * @param nums
     * @return
     */
    public int singleNumber_v2(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        _136 start = new _136();
        System.out.println(start.singleNumber_v2(new int[]{1, 1, 2, 3, 3}));
    }
}
