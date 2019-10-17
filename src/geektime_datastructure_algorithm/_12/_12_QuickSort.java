package geektime_datastructure_algorithm._12;

import java.util.Arrays;

/**
 * 描述:
 * 归并排序，快速排序：属于比较操作的。平均时间复杂度为O(nlogn)优于O(n²)
 * <p>
 * 稳定性和原地排序：
 * 因为归并排序在合并的使用额外的空间：非原地排序，即使它最好、最坏、平均都是O(nlogn)，但是快速排序还是优于它。
 *
 * @author Noah
 * @create 2019-10-16 11:33
 */
public class _12_QuickSort {

    /**
     * 自己第一版本：错误
     * <p>
     * 快速排序:原地排序
     */
    public void quickSort(int[] toSort) {

        int len = toSort.length - 1;
        if (len == 0) {
            return;
        }

        int fixIndex = len;
        int tofix = toSort[fixIndex];
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            //考虑稳定性&&只是确定tofix这个数值的最终位置
            if (toSort[i] <= tofix) {
                continue;
            } else {
                //值交换&&坐标也更改了
                int tem = toSort[i];
                toSort[i] = toSort[fixIndex];
                toSort[fixIndex] = tem;
                fixIndex = i;
            }
        }
        //左边
        quickSort(Arrays.copyOfRange(toSort, 0, fixIndex));
        quickSort(Arrays.copyOfRange(toSort, fixIndex, toSort.length));

    }

    // 快速排序，a是数组，n表示数组的大小
    public void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    // 快速排序递归函数，p,r为下标
    private void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        System.out.println("i=" + i);
        return i;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 9, 3, 4};
        _12_QuickSort app = new _12_QuickSort();

        app.quickSortInternally(a, 0, a.length - 1);

        System.out.println(Arrays.toString(a));

    }
}
