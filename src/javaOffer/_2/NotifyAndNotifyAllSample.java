package javaOffer._2;

/**
 * 描述:
 * notify和notifyAll的区别
 *
 * @author Noah
 * @create 2020-02-25 13:18
 */
public class NotifyAndNotifyAllSample {

    public static boolean go = false;

    public static final Object lock = new Object();

    public static void main(String[] args) {

        Runnable waitThread = new Runnable() {
            @Override
            public void run() {
                shuouldNotGo();
                System.out.println(Thread.currentThread() + "finish");
            }
        };

        Runnable notifyThread = new Runnable() {

            @Override
            public void run() {
                shouldGo();
                System.out.println(Thread.currentThread() + "finish");
            }
        };

        Thread w1 = new Thread(waitThread, "w1");
        Thread w2 = new Thread(waitThread, "w2");
        Thread w3 = new Thread(waitThread, "w3");

        w1.start();
        w2.start();
        w3.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread n1 = new Thread(notifyThread, "n1");
        n1.start();
    }

    private static void shuouldNotGo() {

        if (!go) {
            System.out.println(Thread.currentThread() + "not go step 1");
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "not go step 2");
                try {
                    lock.wait();
                    System.out.println(Thread.currentThread() + "not go step 3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            go = false;

        }
    }

    private static void shouldGo() {
        if (!go) {
            System.out.println(Thread.currentThread() + "go step 1");
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "go step 2");
                go = true;
                lock.notifyAll();
            }

        }
    }


}
