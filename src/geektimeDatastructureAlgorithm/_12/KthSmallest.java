package geektimeDatastructureAlgorithm._12;

import java.util.Arrays;

/**
 * 描述:
 * <p>
 * 参考版：
 * <p>
 * 快排O(n)内查找第K大元素
 *
 * @author Noah
 * @create 2019-11-07 08:21
 */
public class KthSmallest {
    public static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }

        int partition = partition(arr, 0, arr.length - 1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }

        return arr[partition];
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];

        int i = p;
        for (int j = p; j < r; j++) {
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);

        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        int[] a = new int[]{1, 2, 4, 10, 11};
        System.out.println(Arrays.toString(a));
        int k = 1;
        System.out.println("第" + k + "大元素为=" + kthSmallest(a, k));

    }
}
