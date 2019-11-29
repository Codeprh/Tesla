package leetcode;

import java.util.Arrays;

/**
 * 描述:
 * 未排序数组中，没有出现的最小正整数
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 * @author Noah
 * @create 2019-11-29 10:44
 */
public class _41 {

    public static void main(String[] args) {
        _41 app = new _41();
        int[] nums = new int[]{1,2,0};
        int r = app.firstMissingPositive(nums);
        System.out.println("未排序数组中，没有出现的最小正整数=" + r);
    }

    /**
     * 未排序数组中，没有出现的最小整数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {

        int minInt = 1;
        Arrays.sort(nums);

        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {

            //特殊处理1的请求
            if (nums[i] == 1) {
                flag = true;
                minInt = 1;
                break;
            }

            if (nums[i] <= minInt && nums[i] > 0) {
                minInt = ++nums[i];
            }
        }

        while (flag) {

            ++minInt;
            //说明存在
            if (Arrays.binarySearch(nums, minInt) < 0) {
                break;
            }

        }

        return minInt;
    }
}
