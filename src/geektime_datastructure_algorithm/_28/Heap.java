package geektime_datastructure_algorithm._28;

import geektime_datastructure_algorithm._29.HeapSort;

import java.util.Arrays;

/**
 * 描述:
 * 参考版本
 * 数据结构：堆，一种特殊的树
 *
 * @author Noah
 * @create 2019-11-16 12:48
 */
public class Heap {

    public int[] a; // 数组，从下标1开始存储数据
    public int n; // 堆可以存储的最大数据个数
    public int count; // 堆中已经存储的数据个数

    /**
     * 构造函数：初始化数据
     */
    public Heap() {
        this.a = new int[]{6, 1, 2, 4, 5, 7};
        this.n = a.length + 2;
        this.count = a.length;
    }

    public static void main(String[] args) {

        Heap app = new Heap();
        HeapSort.buildHeap(app.a);

        System.out.println(Arrays.toString(app.a));

        int[] temp = new int[app.n];
        System.arraycopy(app.a, 0, temp, 1, app.a.length);
        app.a = temp;
        System.out.println(Arrays.toString(app.a));

        app.insert(10);
        System.out.println(Arrays.toString(app.a));

    }

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    /**
     * 堆插入操作：自下而上堆化，数组从下标0开始,大顶堆
     *
     * @param data
     */
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
