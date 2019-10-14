package concurrent._16;

import java.util.Queue;

/**
 * 描述:
 * 信号量本质
 *
 * @author Noah
 * @create 2019-10-12 08:58
 */
public class _16_Semaphore {
    /**
     * 计数器
     */
    int count;

    /**
     * 等待队列
     */
    Queue queue;

    /**
     * 初始化计数器
     *
     * @param count
     */
    public _16_Semaphore(int count) {
        this.count = count;
    }

    /**
     * 计数器的值减 1;如果此时计数器的值小于 0，
     * <p>
     * 则当前线程将被阻塞，否则当前线程 可以继续执行。
     */
    public void down() {
        count--;
        if (count < 0) {
            //当前线程加入等待队列
            //阻塞当前线程
        }else {
            //执行流程，不阻塞
        }
    }

    /**
     * 计数器的值加 1;如果此时计数器的值小于或者等于 0，
     * <p>
     * 则唤醒等待队列中的一个线 程，并将其从等待队列中移除。
     */
    public void up() {

        count++;
        if (count <= 0) {
            //移除等待队列
            //唤醒等待线程
        }
    }
}
