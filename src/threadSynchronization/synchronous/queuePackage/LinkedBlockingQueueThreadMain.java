package threadSynchronization.synchronous.queuePackage;

import threadSynchronization.Bank;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:
 * 采用阻塞队列实现同步
 *
 * @author codingprh
 * @create 2018-12-19 11:16 AM
 */
public class LinkedBlockingQueueThreadMain {


    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue1 = new LinkedBlockingQueue<>();
        Bank bank = new Bank();
        Integer num = 10;
        Integer result = 0;
        //消费线程
        new Thread(() -> {
            //消费线程
            try {
                Integer money = 0;
                while ((money = queue1.take()) >= 0) {
                    Integer account = bank.addAccount(money);
                    System.out.println(Thread.currentThread().getName() + ",用户余额=" + account + ",queue长度=" + queue1.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        //生成线程
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            //异步线程
            result += num;

            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    queue1.put(10);
                    System.out.println(Thread.currentThread().getName() + ",队列长度=" + queue1.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        queue1.put(-1);
        Thread.sleep(5000);

        System.out.println("用户最终余额=" + bank.getAccount());
        System.out.println("正确用户余额=" + result);
    }

}