package geektime_datastructure_algorithm._15;

/**
 * 描述:
 * 自己实现版本
 * 二分查找
 *
 * @author Noah
 * @create 2019-11-08 11:01
 */
public class MyBinarySearch {
    /**
     * 非递归实现
     * <p>
     * 二分查找，在有序数组中查找某个元素是否存在。时间复杂度O(logn)
     * 不存在重复元素
     *
     * @param a
     * @param val
     * @return
     */
    public static int myBinarySearch(int[] a, int val) {

        int low = 0;
        int high = a.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (a[mid] == val) {
                return mid;
            }

            if (val > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 基于递归实现的二分查找
     *
     * @param a
     * @param val
     * @return
     */
    public static int myBinarySearchRecursive(int[] a, int val) {
        return recursiveCommon(a, 0, a.length - 1, val);
    }

    public static int recursiveCommon(int[] a, int low, int high, int val) {

        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (a[mid] == val) {
            return mid;
        } else if (val < a[mid]) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return recursiveCommon(a, low, high, val);

    }

    public static void main(String[] args) {

        int[] a = new int[]{1, 2, 3, 4, 8, 9};
        System.out.println("经过二分查找，找到下标坐标=" + myBinarySearchRecursive(a, 8));

    }
}
