package swordFingerOffer.book;

/**
 * 描述:
 * 连续子数组的最大和
 *
 * @author Noah
 * @create 2020-08-27 8:45 上午
 */
public class p42_MaximumSumfConsecutiveSubarrays {

    public static void main(String[] args) {

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        p42_MaximumSumfConsecutiveSubarrays app = new p42_MaximumSumfConsecutiveSubarrays();
        System.out.println(app.maxSubArray_right(nums));
    }

    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int maxSubArray_right(int[] nums) {

        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int max = Math.max(nums[i - 1], 0);
            nums[i] += max;
            res = Math.max(res, nums[i]);
        }
        return res;
    }


}
