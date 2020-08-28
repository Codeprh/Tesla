package swordFingerOffer.book;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 数组中出现次数超过一半的数字
 *
 * @author Noah
 * @create 2020-08-27 7:18 上午
 */
public class p39_NumbersArraysOccurMoreThanHalf {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        p39_NumbersArraysOccurMoreThanHalf app = new p39_NumbersArraysOccurMoreThanHalf();
        System.out.println(app.majorityElement(nums));
    }

    /**
     * 最佳解法:投票法
     *
     * @param nums
     * @return
     */
    public int majorityElement_right(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }

    /**
     * 利用排序求众数
     *
     * @param nums
     * @return
     */
    public int majorityElement_right2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * noah自己的版本
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {

            int left = nums[i];
            int right = nums[j];

            if (i == j) {
                count(map, left);
            } else {
                count(map, left);
                count(map, right);
            }

        }

        Integer max = map.entrySet().stream().max(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        }).map(o -> o.getKey()).orElse(0);

        return max;
    }

    private void count(Map<Integer, Integer> map, int key) {

        if (map.containsKey(key)) {
            int leftCount = map.get(key);
            leftCount++;
            map.put(key, leftCount);
        } else {
            map.put(key, 1);
        }

    }
}
