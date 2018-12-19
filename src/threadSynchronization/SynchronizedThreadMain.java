package threadSynchronization;

import threadSynchronization.synchronous.SynchronizedMethodBank;

import java.util.concurrent.CountDownLatch;

/**
 * 描述:
 * 线程主方法
 *
 * @author codingprh
 * @create 2018-12-18 3:44 PM
 */
public class SynchronizedThreadMain {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new SynchronizedMethodBank();
        Integer num = 10;
        Integer result = 0;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            //异步线程
            result += num;

            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    Integer account = bank.addAccount(10);
                    System.out.println("线程名=" + Thread.currentThread().getName() + ",当前用户余额=" + account);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();

        }
        countDownLatch.await();
        System.out.println("用户最终余额=" + bank.getAccount());
        System.out.println("正确用户余额=" + result);
    }
}