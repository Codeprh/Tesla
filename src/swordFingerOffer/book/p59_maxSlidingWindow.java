package swordFingerOffer.book;

import java.util.*;

/**
 * 描述:
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * @author Noah
 * @create 2020-08-30 4:57 下午
 */
public class p59_maxSlidingWindow {

    public static void main(String[] args) {

        p59_maxSlidingWindow app = new p59_maxSlidingWindow();

        System.out.println(Arrays.toString(app.maxSlidingWindow_right2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

    }


    public int[] maxSlidingWindow_right2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        // 未形成窗口
        for (int i = 0; i < k; i++) {

            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }

            deque.addLast(nums[i]);
        }

        res[0] = deque.peekFirst();

        // 形成窗口后
        for (int i = k; i < nums.length; i++) {

            if (deque.peekFirst() == nums[i - k]) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }

            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();

        }
        return res;
    }


    /**
     * 使用队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow_right(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];

        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {

            if (i > 0 && deque.peekFirst() == nums[i - 1]) {
                deque.removeFirst(); // 删除 deque 中对应的 nums[i-1]
            }

            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast(); // 保持 deque 递减
            }

            deque.addLast(nums[j]);

            if (i >= 0) {
                res[i] = deque.peekFirst();  // 记录窗口最大值
            }

        }
        return res;
    }


    /**
     * noah版本:窗口滑动，一个个算
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length <= 0) {
            return new int[]{};
        }

        List<Integer> list = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (j <= nums.length) {
            //右坐标，右移动
            if (j - i < k) {
                j++;
            } else if (j - i == k) {

                int x = i;
                int y = j;

                list.add(maxInt(Arrays.copyOfRange(nums, x, y)));
                i++;

            }
        }
        return list.stream().mapToInt(o -> o).toArray();

    }

    private int maxInt(int[] array) {

        return Arrays.stream(array).max().getAsInt();

    }
}
