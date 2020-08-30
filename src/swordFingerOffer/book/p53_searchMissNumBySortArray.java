package swordFingerOffer.book;

/**
 * 描述:
 * 查找有序数组中缺少的数字。
 * <p>
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * @author Noah
 * @create 2020-08-30 8:31 上午
 */
public class p53_searchMissNumBySortArray {

    public static void main(String[] args) {
        p53_searchMissNumBySortArray app = new p53_searchMissNumBySortArray();
        System.out.println(app.missingNumber(new int[]{1, 2, 3}));//0
        System.out.println(app.missingNumber(new int[]{0, 1}));//2
        System.out.println(app.missingNumber(new int[]{0, 1, 3}));//2
        System.out.println(app.missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9}));//8
        //[]
        System.out.println(app.missingNumber(new int[]{0, 1, 3, 4, 5, 6, 7, 8, 9}));//2


    }

    /**
     * 不服
     *
     * @param nums
     * @return
     */
    public int missingNumber_right(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }


    /**
     * noah版本：有序特性，二分查找,错误版本
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        //当成参数校验
        if (nums.length == 1 && nums[0] != 1) {
            return 1;
        }


        //找右边
        while (left <= right) {

            int m = (left + right) / 2;
            if (nums[m] != m && nums[0] == 0) {
                return m;
            }

            left = m + 1;

        }

        left = 0;
        right = nums.length - 1;
        //找左边
        while (left <= right) {

            int m = (left + right) / 2;
            if (nums[m] != m && nums[0] == 0) {
                return m;
            }

            right = m - 1;
            //处理sb情况[0,1]返回2
            if (right == -1) {
                if (nums[nums.length - 1] != nums.length) {
                    return nums.length;
                }

                if (nums[0] != 0) {
                    return 0;
                }
            }

        }

        return 0;
    }

}
