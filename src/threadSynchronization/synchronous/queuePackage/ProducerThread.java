package threadSynchronization.synchronous.queuePackage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:
 * 生产线程
 *
 * @author codingprh
 * @create 2018-12-19 2:46 PM
 */
public class ProducerThread implements Runnable {

    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public ProducerThread(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            if (queue.size() == 9) {
                queue.put(-1);
            }
            queue.put(10);
            System.out.println("生产，当前queue长度=" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}