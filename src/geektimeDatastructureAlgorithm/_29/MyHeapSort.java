package geektimeDatastructureAlgorithm._29;

import java.util.Arrays;

/**
 * 描述:
 * 堆排序：自己实现版本
 *
 * @author Noah
 * @create 2019-10-25 11:25
 */
public class MyHeapSort {
    public static void main(String[] args) {

        int[] a = {6, 1, 2, 4, 5, 7, 9,10};
        //1、建堆。2、排序
        buildHeap(a);
        int k = a.length - 1;
        while (k > 0) {
            swap(a, k, 0);
            heap(a, --k, 0);
        }

        System.out.println(Arrays.toString(a));
    }

    /**
     * 大顶堆
     * <p>
     * 7-5-6-4-2-1
     * 大顶堆：每个节点都大于子节点。
     * <p>
     * 访问节点：左节点2*i+1,右节点2*i+2（下标0开始计算）
     * <p>
     * 存储在数组中：完全二叉树
     * <p>
     * 从(n-1)/2节点（最后一个非叶子节点=为最后一个叶子节点的父节点）开始堆化
     *
     * @param a
     */
    public static void buildHeap(int a[]) {

        for (int i = ((a.length - 1) / 2); i >= 0; i--) {
            heap(a, a.length - 1, i);
        }
    }

    /**
     * 堆化：
     *
     * @param a
     * @param n ：最后堆元素的下标
     * @param i
     */
    public static void heap(int[] a, int n, int i) {
        while (true) {

            int maxPoint = i;

            //和左坐标比较
            if (2 * i + 1 <= n && a[i] < a[2 * i + 1]) {
                maxPoint = 2 * i + 1;
            }
            if (2 * i + 2 <= n && a[maxPoint] < a[2 * i + 2]) {
                maxPoint = 2 * i + 2;
            }
            if (maxPoint == i) {
                break;
            }

            swap(a, i, maxPoint);
            //以交换后子节点位置接着往下查找:新增、移除堆顶的时候使用
            i = maxPoint;

        }

    }

    /**
     * 交换值
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
