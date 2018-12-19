package threadSynchronization.synchronous.queuePackage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:
 * 消费线程
 *
 * @author codingprh
 * @create 2018-12-19 2:44 PM
 */
public class ConsumerThread implements Runnable {

    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public ConsumerThread(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public ConsumerThread() {
    }

    @Override
    public void run() {
        try {
            while (queue.take() != -1) {
                System.out.println("消费，" + Thread.currentThread().getName() + ",queue长度=" + queue.size());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}