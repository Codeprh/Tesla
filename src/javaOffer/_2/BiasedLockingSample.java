package javaOffer._2;

/**
 * 描述:
 * 偏向锁栗子
 *
 * @author Noah
 * @create 2020-03-25 15:03
 */
public class BiasedLockingSample {

    public static final Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            try {

                synchronized (lock) {
                    Thread.sleep(10000);
                    System.out.println("hello" + i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
