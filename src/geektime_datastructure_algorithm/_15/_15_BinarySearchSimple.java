package geektime_datastructure_algorithm._15;

/**
 * 描述:
 * 基于递归实现的二分查找
 *
 * @author Noah
 * @create 2019-11-08 09:34
 */
public class _15_BinarySearchSimple {

    // 二分查找的递归实现
    public int bsearch(int[] a, int n, int val) {
        return bsearchInternally(a, 0, n - 1, val);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearchInternally(a, mid + 1, high, value);
        } else {
            return bsearchInternally(a, low, mid - 1, value);
        }
    }

    //非递归实现
    public int binarySearch(int value, int[] r) {
        int low = 0;
        int high = r.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (r[mid] == value) {
                return mid;
            } else if (r[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;

    }
}
