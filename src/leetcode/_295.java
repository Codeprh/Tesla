package leetcode;

import java.util.Arrays;

/**
 * 描述:
 * leetcode中位数求解：
 * <p>
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 * <p>
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * @author Noah
 * @create 2019-11-20 11:00
 */
public class _295 {


    public static void main(String[] args) {
        //int[] a = new int[]{2, 1, 3};
        int[] a = new int[]{6, 10, 2, 6, 5, 0};
        MedianFinder medianFinder = new MedianFinder();

        for (int aa : a) {
            medianFinder.addNum(aa);
        }
        System.out.println("中位数=" + medianFinder.findMedian());

        System.out.println("有序数组=" + Arrays.toString(a) + ",数组长度=" + a.length + ",理论中位数=" + (a.length % 2 == 0 ? medianFinder.divide((a[a.length / 2] + a[a.length / 2 - 1]), 2, 1) : a[a.length / 2]));

    }

    public static class MedianFinder {

        /**
         * 小顶堆、大顶堆定义
         */
        public int[] small;
        public int[] big;

        /**
         * 小顶/大顶堆存储元素个数
         */
        public int sc = 0;
        public int bc = 0;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            //todo：扩容
            small = new int[1000];
            big = new int[1000];
        }

        public void addNum(int num) {
            insert(num, false);
            balanceData();
        }

        public double findMedian() {
            //只有一个元素的情况
            if (sc == 1 && bc == 0) {
                return small[1];
            }
            int n = sc + bc;
            return n % 2 == 0 ? divide((small[1] + big[1]), 2, 1) : big[1];

        }

        /**
         * 除法
         *
         * @param divisor
         * @param dividend
         * @param scale
         * @return
         */
        public double divide(double divisor, double dividend, int scale) {

            if (dividend == 0 || divisor == 0) {
                return 0;
            }

            //BigDecimal b = new BigDecimal(divisor);
            //BigDecimal dv = b.divide(new BigDecimal(dividend), scale, BigDecimal.ROUND_HALF_UP);

            return (double) divisor / dividend;
        }

        /**
         * 通用插入大小顶堆数据
         *
         * @param a
         * @param forceSmall 强制插入小顶堆
         */
        public void insert(int a, boolean forceSmall) {

            if (sc == 0) {
                small[++sc] = a;
                return;
            }

            if (small[1] < a || forceSmall) {
                ++sc;
                small[sc] = a;
                heapfy(small, sc, true);
            } else {
                ++bc;
                big[bc] = a;
                heapfy(big, bc, false);
            }
        }


        /**
         * 平衡数据
         */
        public void balanceData() {

            //奇偶数刚好平衡的情况
            if (bc - sc >= 0 && bc - sc <= 1) {
                return;
            }

            int n = bc + sc;
            int diff;

            boolean removeSmall = true;
            boolean evenNumber = false;
            if (n % 2 == 0) {
                evenNumber = true;
            }

            //小顶堆>大顶堆
            if (sc > bc) {
                if (evenNumber) {
                    diff = n / 2 - bc;
                } else {
                    diff = n / 2 - bc + 1;
                }
            } else {
                diff = n / 2 - sc;
                removeSmall = false;
            }

            while (diff > 0) {

                if (removeSmall) {
                    int temp = small[1];
                    --sc;
                    remove(small, sc, removeSmall);
                    insert(temp, false);
                } else {
                    int temp = big[1];
                    --bc;
                    remove(big, bc, removeSmall);
                    insert(temp, true);
                }
                diff--;
            }

        }

        /**
         * 从上往下堆化，场景：移除堆顶元素
         */
        public void remove(int[] arr, int count, boolean small) {

            arr[1] = arr[count + 1];


            int i = 1;
            while (true) {

                int minPos = i;

                if (i * 2 <= count && (small == true ? arr[minPos] > arr[i * 2] : arr[minPos] < arr[i * 2])) {
                    minPos = i * 2;
                }

                if (i * 2 + 1 <= count && (small == true ? arr[minPos] > arr[i * 2 + 1] : arr[minPos] < arr[i * 2 + 1])) {
                    minPos = i * 2 + 1;
                }

                if (minPos == i) {
                    break;
                }

                swap(arr, minPos, i);
                i = minPos;

            }


        }

        /**
         * 从下往上堆化，场景：插入
         *
         * @param a
         * @param count
         */
        public void heapfy(int[] a, int count, boolean small) {

            while (count / 2 > 0 && (small == true ? a[count] < a[count / 2] : a[count] > a[count / 2])) {
                swap(a, count, count / 2);
                count = count / 2;

            }

        }

        /**
         * 数组数据交换
         *
         * @param a
         * @param i
         * @param pi
         */
        public void swap(int[] a, int i, int pi) {
            int temp = a[i];
            a[i] = a[pi];
            a[pi] = temp;
        }
    }

}
