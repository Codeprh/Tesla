package swordFingerOffer.book;

/**
 * 描述:
 * 在排序数组中查找数字 I，出现的次数
 *
 * @author Noah
 * @create 2020-08-29 8:51 上午
 */
public class p53_searchNumBySortArray {

    public static void main(String[] args) {

        p53_searchNumBySortArray app = new p53_searchNumBySortArray();

        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(app.search_noah_right(nums, 7));

    }

    /**
     * noah，二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search_noah_right(int[] nums, int target) {
        //处理right
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            //求得中间下标
            int m = (left + right) / 2;

            if (target < nums[m]) {
                right = m - 1;
            } else {
                left = m + 1;
            }
        }

        //无匹配结果提前返回
        if (right >= 0 && nums[right] != target) {
            return 0;
        }

        int rightR = right;

        //处理左边
        left = 0;
        right = nums.length - 1;

        while (left <= right) {

            int m = (left + right) / 2;
            if (nums[m] < target) {
                left = m + 1;
            } else {
                right = m - 1;
            }
        }


        return rightR - left + 1;
    }

    /**
     * 利用二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search_right(int[] nums, int target) {

        // 搜索右边界 right
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= target) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }

        int right = i;
        // 若数组中无 target ，则提前返回
        if (j >= 0 && nums[j] != target) {
            return 0;
        }
        // 搜索左边界 right
        i = 0;
        j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] < target) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        int left = j;
        return right - left - 1;
    }


    /**
     * noah版本，没有使用到有序的特性去实现。（有序=二分）
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        int count = 0;
        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {

            //处理相遇情况
            if (i == j) {

                if (nums[i] == target) {
                    count++;
                    break;
                }

            }

            if (nums[i] == target) {
                count++;
            }

            if (nums[j] == target) {
                count++;
            }
        }
        return count;
    }


}
