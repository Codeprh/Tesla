package leetcode;

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
        int[] nums = new int[]{7,8,9,11,12};
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

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= minInt && nums[i] > 0) {
                minInt = ++nums[i];
            }
        }

        return minInt;
    }
}
