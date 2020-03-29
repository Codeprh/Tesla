package javaOffer._2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * 可重入锁栗子
 *
 * @author Noah
 * @create 2020-02-27 09:36
 */
public class ReentrantLockSample implements Runnable {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {

        ReentrantLockSample rls = new ReentrantLockSample();

        Thread t1 = new Thread(rls);
        Thread t2 = new Thread(rls);

        t1.start();
        t2.start();


    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
