package swordFingerOffer.book;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * @author Noah
 * @create 2020-09-02 7:02 上午
 */
public class p21_exchange {

    public static void main(String[] args) {

    }

    /**
     * 双向指针大法
     *
     * @param nums
     * @return
     */
    public int[] exchange_right(int[] nums) {
        int i = 0, j = nums.length - 1, tmp;
        while (i < j) {
            while (i < j && (nums[i] & 1) == 1) i++;
            while (i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }


    /**
     * noah版本
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {

        List<Integer> head = new ArrayList<>();
        List<Integer> last = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                last.add(nums[i]);
            } else {
                head.add(nums[i]);
            }
        }

        head.addAll(last);

        return head.stream().mapToInt(o -> o).toArray();

    }
}
