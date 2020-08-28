package geektimeDatastructureAlgorithm._9;

import java.util.Arrays;

/**
 * 描述:
 * <p>
 * 循环队列实现：
 * 解决tail==n，数据搬迁问题。
 *
 * @author Noah
 * @create 2019-10-31 09:15
 */
public class _9_CircularQueue {
    // 数组:items，数组大小:n
    private String[] items;
    private int n = 0;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为capacity的数组
    public _9_CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        if ((tail + 1) % n == head) {
            return false;
        }
        // 队列满了
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }

    @Override
    public String toString() {
        return "_9_CircularQueue{" +
                "items=" + Arrays.toString(items) +
                ", n=" + n +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    public static void main(String[] args) {

        _9_CircularQueue app = new _9_CircularQueue(5);

        app.enqueue("i");
        app.enqueue("feel");
        app.enqueue("down");

        app.dequeue();

        System.out.println(app.toString());

    }
}
