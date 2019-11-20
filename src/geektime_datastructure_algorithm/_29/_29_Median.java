package geektime_datastructure_algorithm._29;

import java.util.Arrays;

/**
 * 描述:
 * <p>
 * 中位数
 * <p>
 * 静态数据：直接排序然后n/2
 * <p>
 * 动态数据求解：维护两个堆，大顶堆(n/2+1~n)和小顶堆（n/2），但是小顶堆的元素都大于大顶堆的元素。中位数就是大顶堆的堆顶
 * n为奇数：大顶堆元素个数多一个。
 * n为偶数：大小顶堆相等
 *
 * @author Noah
 * @create 2019-11-18 16:09
 */
public class _29_Median {

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
     * 平衡大小堆
     */
    public int medianSize;

    public _29_Median(int n) {
        medianSize = n / 2;
        small = new int[n + 1];
        big = new int[n + 1];
    }

    public static void main(String[] args) {
        //int[] a = new int[]{2, 1, 3, 4, 6, 7, 8};
        //int[] a = new int[]{2, 1, 3};
        //int[] a = new int[]{10, 9, 8, 7, 6};
        //int[] a = new int[]{1, 2, 3, 4, 5};


        //int[] a = new int[]{2, 1, 3, 4};
        //int[] a = new int[]{1, 2, 3, 4};
        int[] a = new int[]{4, 3, 2, 1};
        //todo：leetcode，求中位数题目

        _29_Median app = new _29_Median(a.length);

        for (int aa : a) {
            app.insert(aa, false);
        }
        //平衡数据
        app.balanceData();
        System.out.println("中位数=" + app.big[1]);

        Arrays.sort(a);
        System.out.println("有序数组=" + Arrays.toString(a) + ",数组长度=" + a.length + ",理论中位数=" + a[a.length / 2]);

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

            if (i * 2 + 1 <= count && (small == true ? arr[minPos] > arr[i * 2 + 1] : arr[minPos] < arr[i * 2+1])) {
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
