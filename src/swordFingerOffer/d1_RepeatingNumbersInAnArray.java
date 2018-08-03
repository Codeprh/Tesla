package swordFingerOffer;

import java.util.Arrays;

/**
 * 题目：数组中重复的数字
 * <p>
 * 需求：在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * <p>
 * 解题思路：这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素放到第 i 个位置上。
 */
public class d1_RepeatingNumbersInAnArray {
    public int findRepeatingNumber(int[] arrays) {
        for (int cursor = 0, n = arrays.length; cursor < n; cursor++) {
            if (arrays == null || arrays.length <= 0) {
                return -1;
            }
            int cursorValue = arrays[cursor];
            int goal = cursorValue;
            while (cursorValue != cursor) {
                if (arrays[cursor] == arrays[cursorValue]) {
                    return arrays[cursorValue];
                }
                swap2(arrays, cursor, arrays[cursor]);

                System.out.println(Arrays.toString(arrays));
            }
        }
        return -1;
    }

    public void swap2(int[] arrays, int cursor, int goal) {
        int cursorValue = arrays[cursor];
        arrays[cursor] = arrays[goal];
        arrays[goal] = cursorValue;
    }

    public static void main(String[] args) {
        int[] arrays = new int[]{0, 3, 1, 4, 5, 0};
        int[] result = new int[arrays.length];
        d1_RepeatingNumbersInAnArray app = new d1_RepeatingNumbersInAnArray();
        System.out.println(app.findRepeatingNumber(arrays));
//        app.duplicate(arrays,arrays.length,result);
//        System.out.println("=====");
//        System.out.println(Arrays.toString(result));

    }

    public boolean duplicate(int[] nums, int length, int[] duplication) {
        if (nums == null || length <= 0)
            return false;
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplication[0] = nums[i];
                    return true;
                }
                swap(nums, i, nums[i]);
                System.out.println(Arrays.toString(nums));
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
