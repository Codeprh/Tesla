package javaOffer._2;

/**
 * 描述:
 * 当前线程栗子
 *
 * @author Noah
 * @create 2020-02-24 07:24
 */
public class CurrentThreadSample {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("hello run,currentThread=" + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.run();
        t1.start();
        System.out.println("hello start,currentThread=" + Thread.currentThread().getName());
        t1.start();
    }
}
