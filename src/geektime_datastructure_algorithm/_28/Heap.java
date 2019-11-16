package geektime_datastructure_algorithm._28;

/**
 * 描述:
 * 数据结构：堆，一种特殊的树
 *
 * @author Noah
 * @create 2019-11-16 12:48
 */
public class Heap {

    private int[] a; // 数组，从下标1开始存储数据
    private int n; // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
            swap(a, i, i / 2); // swap()函数作用:交换下标为i和i/2的两个元素
            i = i / 2;
        }
    }

    /**
     * 两个坐标下来的值交换
     *
     * @param a
     * @param i
     * @param j
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
