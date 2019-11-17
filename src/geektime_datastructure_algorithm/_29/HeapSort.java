package geektime_datastructure_algorithm._29;

import java.util.Arrays;

/**
 * 描述:
 * 堆排序的实现
 * <p>
 * 乱序的数组：利用堆排序思想排序。
 * 第一步：建堆（对每个值堆化）
 * 第二步：排序
 * <p>
 * 堆是
 *
 * @author Noah
 * @create 2019-10-22 16:24
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] a = {6, 1, 2, 4, 5, 7};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 排序
     * <p>
     * 堆元素是从数组下标0开始
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        // 1、建堆
        buildHeap(arr);
        System.out.println(Arrays.toString(arr));
        // 2、排序
        int k = arr.length - 1;
        while (k > 0) {
            // 将堆顶元素（最大）与最后一个元素交换位置
            swap(arr, 0, k);
            // 将剩下元素重新堆化，堆顶元素变成最大元素
            heapify(arr, --k, 0);
        }
    }

    /**
     * 建堆
     *
     * @param arr
     */
    public static void buildHeap(int[] arr) {
        // (arr.length - 1) / 2 为最后一个叶子节点的父节点
        // 也就是最后一个非叶子节点，依次堆化直到根节点
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
    }

    /**
     * 堆化
     *
     * @param arr 要堆化的数组
     * @param n   最后堆元素下标
     * @param i   要堆化的元素下标
     */
    private static void heapify(int[] arr, int n, int i) {
        while (true) {
            // 最大值位置
            int maxPos = i;
            // 与左子节点（i * 2 + 1）比较，获取最大值位置
            if (i * 2 + 1 <= n && arr[i] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 最大值与右子节点（i * 2 + 2）比较，获取最大值位置
            if (i * 2 + 2 <= n && arr[maxPos] < arr[i * 2 + 2]) {
                maxPos = i * 2 + 2;
            }
            // 最大值是当前位置结束循环
            if (maxPos == i) {
                break;
            }
            // 与子节点交换位置
            swap(arr, i, maxPos);
            // 以交换后子节点位置接着往下查找
            i = maxPos;
        }
    }

    /**
     * 两个坐标下来的值交换
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
