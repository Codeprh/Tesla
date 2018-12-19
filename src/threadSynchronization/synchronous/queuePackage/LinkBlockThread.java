package threadSynchronization.synchronous.queuePackage;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:
 * 线程
 *
 * @author codingprh
 * @create 2018-12-19 3:09 PM
 */
public class LinkBlockThread implements Runnable {

    private LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

    /**
     * 定义生产商品个数
     */
    private static final int size = 10;
    /**
     * 定义启动线程的标志，为0时，启动生产商品的线程；为1时，启动消费商品的线程
     */
    private int flag = 0;

    public LinkBlockThread(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public LinkBlockThread() {
    }

    @Override
    public void run() {
        int new_flag = flag++;
        System.out.println("启动线程 " + new_flag);
        if (new_flag == 0) {
            for (int i = 0; i < size; i++) {
                int b = new Random().nextInt(255);
                System.out.println("生产商品：" + b + "号");
                try {
                    queue.put(b);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("仓库中还有商品：" + queue.size() + "个");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < size / 2; i++) {
                try {
                    int n = queue.take();
                    System.out.println("消费者买去了" + n + "号商品");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("仓库中还有商品：" + queue.size() + "个");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }

}