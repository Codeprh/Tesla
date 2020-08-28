package geektimeDatastructureAlgorithm._28;

/**
 * 描述:
 * 堆排序：从下标1开始
 *
 * @author Noah
 * @create 2019-11-18 08:53
 */
public class _28_HeapSort {

    public static void main(String[] args) {

        int[] a = new int[]{};
        sort(a, a.length);

    }

    /**
     * 建堆
     *
     * @param a
     * @param n
     */
    private static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    /**
     * 堆化
     *
     * @param a
     * @param n
     * @param i
     */
    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
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

    /**
     * n表示数据的个数，数组a中的数据从下标1到n的位置。
     *
     * @param a
     * @param n
     */
    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

}
