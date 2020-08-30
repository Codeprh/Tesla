package swordFingerOffer.book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * @author Noah
 * @create 2020-08-30 11:28 上午
 */
public class p57_twoSum {

    public static void main(String[] args) {

        //int[] nums = new int[]{2, 7, 11, 15};
        int[] nums = new int[]{2, 3, 9, 41, 46, 47};

        p57_twoSum app = new p57_twoSum();
        System.out.println(Arrays.toString(app.twoSum_right(nums, 49)));

    }

    /**
     * 使用递增的特性
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum_right(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int s = nums[i] + nums[j];
            if (s < target) {
                i++;
            } else if (s > target) {
                j--;
            } else {
                return new int[]{nums[i], nums[j]};
            }
        }
        return new int[0];
    }

    /**
     * noah版本利用map来实现+双向指针
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] r = new int[2];

        for (int i = 0, j = nums.length - 1; i <= j; j--, i++) {

            int pre = target - nums[i];
            int last = target - nums[j];

            if (map.get(pre) != null) {
                r[0] = pre;
                r[1] = nums[i];
                break;
            } else if (map.get(last) != null) {
                r[0] = last;
                r[1] = nums[j];
                break;
            } else if (pre + last == target) {
                r[0] = nums[i];
                r[1] = nums[j];
                break;
            } else {
                map.put(nums[i], pre);
                map.put(nums[j], last);
            }
        }

        return r;
    }
}
