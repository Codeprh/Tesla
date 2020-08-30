package swordFingerOffer.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 描述:
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * @author Noah
 * @create 2020-08-30 1:38 下午
 */
public class p57_findContinuousSequence {

    public static void main(String[] args) {

        p57_findContinuousSequence app = new p57_findContinuousSequence();

        int[][] continuousSequence_right = app.findContinuousSequence_right(10);

        Stream.of(continuousSequence_right).collect(Collectors.toList()).forEach(o -> System.out.println(Arrays.toString(o)));

    }

    /**
     * 滑动窗口解法
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence_right(int target) {

        int i = 1; // 滑动窗口的左边界
        int j = 1; // 滑动窗口的右边界
        int sum = 0; // 滑动窗口中数字的和

        List<int[]> res = new ArrayList<>();

        while (i <= target / 2) {
            if (sum < target) {
                // 右边界向右移动
                sum += j;
                j++;
            } else if (sum > target) {
                // 左边界向右移动
                sum -= i;
                i++;
            } else {
                // 记录结果
                int[] arr = new int[j - i];
                for (int k = i; k < j; k++) {
                    arr[k - i] = k;
                }
                res.add(arr);
                // 左边界向右移动
                sum -= i;
                i++;
            }
        }

        return res.toArray(new int[res.size()][]);

    }

    /**
     * noah版本：不会
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        return null;
    }
}
