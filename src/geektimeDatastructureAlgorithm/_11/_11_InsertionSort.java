package geektimeDatastructureAlgorithm._11;

import java.util.Arrays;

/**
 * 描述:
 * 冒泡排序，插入排序，选择排序。平均复杂度都是O(n²)
 * <p>
 * 还需要考虑稳定性、原地排序
 * <p>
 * 插入排序：
 *
 * @author Noah
 * @create 2019-10-16 10:15
 */
public class _11_InsertionSort {
    /**
     * 插入排序
     *
     * @param sorts
     * @return
     */
    public void insertionSort(int[] sorts) {

        if (sorts.length <= 0) {
            return;
        }

        for (int i = 1; i < sorts.length; i++) {

            int val = sorts[i];

            for (int j = i - 1; j >= 0; j--) {
                //排序好了的最后一位值
                int sortLast = sorts[j];

                if (val > sortLast) {
                    break;
                } else {
                    //交换值
                    int s1 = sorts[j + 1];
                    int temp = s1;
                    sorts[j + 1] = sorts[j];
                    sorts[j] = temp;
                }

            }

        }
    }

    public static void main(String[] args) {

        int[] a = new int[]{1, 3, 10, 9, -1};
        _11_InsertionSort app = new _11_InsertionSort();

        app.insertionSort(a);

        System.out.println(Arrays.toString(a));


    }


}
