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

        int[] temp = new int[app.n];
        System.arraycopy(app.a, 0, temp, 1, app.a.length);
        app.a = temp;

        System.out.println("原始，" + "数组=" + Arrays.toString(app.a) + ",n=" + app.n + ",count=" + app.count);

        app.insert(10);
        System.out.println("插入，" + "数组=" + Arrays.toString(app.a) + ",n=" + app.n + ",count=" + app.count);

        app.removeMax();
        System.out.println("删除，" + "数组=" + Arrays.toString(app.a) + ",n=" + app.n + ",count=" + app.count);

        app.insert(20);
        System.out.println("插入，" + "数组=" + Arrays.toString(app.a) + ",n=" + app.n + ",count=" + app.count);


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
     * 移除堆顶元素
     */
    public void removeMax() {
        // 堆中没有数据
        if (count == 0) return;

        int temp = a[1];
        a[1] = a[count];
        a[count] = 0;

        --count;
        heapify(a, count, 1);
        System.out.println("移除堆顶元素=" + temp);
    }

    public void buildHeap(int[] arr) {
        // (arr.length - 1) / 2 为最后一个叶子节点的父节点
        // 也就是最后一个非叶子节点，依次堆化直到根节点
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
    }

    /**
     * 自上往下堆化:下标1开始
     *
     * @param a
     * @param n
     * @param i
     */
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
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
