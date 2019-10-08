package concurrent._8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * 使用管程实现的阻塞队列
 *
 * @author Noah
 * @create 2019-09-30 08:53
 */
public class _8_Monitor_BlockQueue<T> {

    /**
     * todo：这个正确吗？
     */
    List<T> queue = new ArrayList<>(3);

    final Lock lock = new ReentrantLock();

    //对于入队操作，如果队列已满，就需要等待直到队列不满，所以这里用了 notFull.await();
    final Condition notFull = lock.newCondition();

    //对于出队操作，如果队列为空，就需要等待直到队列不空，所以就用了 notEmpty.await();
    final Condition notEmpty = lock.newCondition();

    /**
     * 入队操作
     *
     * @param x
     */
    public void enq(T x) {

        try {

            lock.lock();

            while (queue.size() == 3) {
                notFull.await();
            }

            queue.add(x);
            notEmpty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 出队操作
     */
    public void deq() {
        try {

            lock.lock();

            while (queue.isEmpty()) {
                notEmpty.await();
            }

            queue.remove(0);
            notFull.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

    }

}
