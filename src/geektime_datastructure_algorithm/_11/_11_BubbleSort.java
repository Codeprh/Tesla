package geektime_datastructure_algorithm._11;

import java.util.Arrays;

/**
 * 描述:
 * 冒泡排序
 *
 * @author Noah
 * @create 2019-11-04 08:45
 */
public class _11_BubbleSort {
    /**
     * 冒泡排序
     *
     * @param a
     */
    public void bubbleSort(int[] a) {

        int n = a.length - 1;

        for (int i = 0; i < n; i++) {

            //优化：如果没有比较次数了，直接break
            boolean nochange = true;

            for (int j = 0; j < n - i; j++) {

                //这里决定了稳定性
                if (a[j] > a[j + 1]) {
                    int tem = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tem;
                    nochange = false;
                }

            }
            if (nochange) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 10, 9, 2};
        _11_BubbleSort app = new _11_BubbleSort();

        app.bubbleSort(a);

        System.out.println(Arrays.toString(a));
    }
}
