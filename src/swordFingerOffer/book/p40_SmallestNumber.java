package swordFingerOffer.book;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 描述:
 * <p>
 * 最小的k个数：
 * <p>
 * 一、用快排最最最高效解决 TopK 问题：
 * 二、大根堆(前 K 小) / 小根堆（前 K 大),Java中有现成的 PriorityQueue，实现起来最简单：
 * 三、二叉搜索树也可以 解决 TopK 问题哦
 * 四、数据范围有限时直接计数排序就行了：
 *
 * @author Noah
 * @create 2020-08-27 8:19 上午
 */
public class p40_SmallestNumber {

    public static void main(String[] args) {

    }

    /**
     * noah版本，直接排序
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        //直接排序，选择前k个数字
        Arrays.sort(arr);
        int[] r = new int[k];
        //函数截取数组
        for (int i = 0; i < k; i++) {
            r[i] = arr[i];
        }
        return r;
    }

    /**
     * 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
     * 1. 若目前堆的大小小于K，将当前数字放入堆中。
     * 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大，这个数就直接跳过；
     * 反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers_right(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num : arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for (int num : pq) {
            res[idx++] = num;
        }
        return res;

    }
}
