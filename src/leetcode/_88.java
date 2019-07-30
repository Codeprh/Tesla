package leetcode;

import java.util.Arrays;

/**
 * @author Noah
 * @create 2019-07-29 20:28
 */
public class _88 {
    /**
     * 版本1：使用java api
     * <p>
     * 时间复杂度：O((n+m)log(n+m))
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);

    }

    /**
     * 双指针 / 从前往后
     * <p>
     * 时间复杂度：O(n+m)
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge_v2(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }

    /**
     * 双向指针 / 从后往前
     * <p>
     * 时间复杂度：O(n+m)
     * <p>
     * 不需要额外的空间
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge_v3(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m - 1;
        int p2 = n - 1;

        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }

        //考虑剩余的情况
        if (p2 > 0) {
            System.arraycopy(nums2, p2, nums1, m - p2, n - p2);
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 0, 0, 0};
        int[] b = new int[]{2, 5, 60};

        _88 app = new _88();
        app.merge_v3(a, a.length - b.length, b, b.length);

        for (int a1 : a) {
            System.out.println(a1);
        }


    }
}
