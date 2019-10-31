package geektime_datastructure_algorithm._9;

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
// 队列满了
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    // 出队
    public String dequeue() {
// 如果head == tail 表示队列为空
        if (head == tail) return null;
        String ret = items[head];
        head = (head + 1) % n;
        return ret;
    }
}
