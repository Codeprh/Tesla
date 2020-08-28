package geektimeDatastructureAlgorithm._11;

import java.util.Arrays;

/**
 * 描述:
 * 选择排序
 *
 * @author Noah
 * @create 2019-11-04 09:23
 */
public class _11_SelectionSort {

    /**
     * 选择排序
     *
     * @param a
     */
    public void selectionSort(int[] a) {

        int n = a.length - 1;

        for (int i = 0; i < n; i++) {

            int smallIndex = i;
            for (int j = i + 1; j < n; j++) {

                if (a[smallIndex] > a[j + 1]) {
                    smallIndex = j + 1;
                }
            }
            //swap
            int temp = a[i];
            a[i] = a[smallIndex];
            a[smallIndex] = temp;

        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 10, 9, 2};
        _11_SelectionSort app = new _11_SelectionSort();

        app.selectionSort(a);

        System.out.println(Arrays.toString(a));
    }
}
