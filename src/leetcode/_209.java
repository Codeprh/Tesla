package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 长度最小的子数组
 *
 * @author Noah
 * @create 2019-08-16 10:09
 */
//给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
//
// 示例:
//
// 输入: s = 7, nums = [2,3,1,2,4,3]
//输出: 2
//解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
//
//
// 进阶:
//
// 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
//
public class _209 {
    /**
     * 错误解答：不能解答
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {

        if (nums.length <= 0) {
            return 0;
        }


        int p = 0;
        int reslut = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            reslut += nums[i];
            p++;

            if (reslut >= s) {
                map.put(reslut, p);
                p = 0;
                reslut = 0;
            }
        }

        if (map.isEmpty()) {
            return 0;
        }

        Set<Integer> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return map.get(obj[0]);
    }

    /**
     * 版本2：滑动窗口
     * todo:手写版本？
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_v2(int s, int[] nums) {

        int n = Integer.MAX_VALUE;

        // i: 左边界，j: 右边界，sum: 窗口和，result: 返回值
        int i = 0, j = 0, sum = 0, result = n;


        // 由于左边界必须小于右边界（i <= j ），判断条件可写为 j < n
        while (j < nums.length) {

            // sum = [i,j) 的和，所以需要加上j的值
            if (sum + nums[j] < s) {
                sum += nums[j++];

            } else {

                // i、j为下标，故需要 + 1
                result = Math.min(result, j - i + 1);
                sum -= nums[i++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        _209 app = new _209();
        System.out.println("最小连续长度=" + app.minSubArrayLen_v2(11, new int[]{1, 2, 3, 4, 11}));
    }
}
