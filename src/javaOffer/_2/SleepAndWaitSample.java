package javaOffer._2;

/**
 * 描述:
 * sleep和wait方法
 *
 * @author Noah
 * @create 2020-02-25 09:48
 */
public class SleepAndWaitSample {

    public static void main(String[] args) {

        final Object lock = new Object();
        new Thread(() -> {

            System.out.println("Thread A is waiting to get lock");
            synchronized (lock) {
                System.out.println("Thread A get lock");
                try {
                    Thread.sleep(200);
                    System.out.println("A finish task!!");
                    lock.wait();
                    System.out.println("Thrad A is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            System.out.println("Thread B is waiting to get lock");
            synchronized (lock) {
                System.out.println("Thread B get lock");
                try {
                    System.out.println("Thread B sleep");
                    Thread.sleep(200);

                    System.out.println("Thrad B is done");
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}
