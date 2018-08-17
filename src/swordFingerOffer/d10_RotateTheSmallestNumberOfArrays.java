package swordFingerOffer;

/**
 * 题目： 旋转数组的最小数字
 * <p>
 * 题目描述：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转，该数组的最小值为 1。
 * <p>
 * 解题思路：
 * 在一个有序数组中查找一个元素可以用二分查找，二分查找也称为折半查找，每次都能将查找区间减半，这种折半特性的算法时间复杂度都为 O(logN)。
 * <p>
 * 本题可以修改二分查找算法进行求解：
 * <p>
 * 当 nums[m] <= nums[h] 的情况下，说明解在 [l, m] 之间，此时令 h = m；
 * 否则解在 [m + 1, h] 之间，令 l = m + 1。
 */
//可以把题目理解为使用递增数组，二分查找最小的元素
public class d10_RotateTheSmallestNumberOfArrays {

    public int minNumberInRotateArray(int[] nums) {
        if (nums.length == 0)
            return 0;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }

    //二分查找，适用于有序的队列查找，查找是否存在某位数字，查找最大（小）数字
    public int miniNumberInRotateArrayMe(int[] nums) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] > nums[h]) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 3, 2, 1};
        d10_RotateTheSmallestNumberOfArrays app = new d10_RotateTheSmallestNumberOfArrays();
        System.out.println(app.miniNumberInRotateArrayMe(nums));
    }
}
