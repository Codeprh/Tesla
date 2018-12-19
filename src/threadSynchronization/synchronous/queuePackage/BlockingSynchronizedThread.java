package threadSynchronization.synchronous.queuePackage;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:
 * demo
 *
 * @author codingprh
 * @create 2018-12-19 3:06 PM
 */
public class BlockingSynchronizedThread {

    public static void main(String[] args) {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            new Thread(new ProducerThread(queue)).start();
        }
        new Thread(new ConsumerThread(queue)).start();

    }

}