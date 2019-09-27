package concurrent.utils;

/**
 * 描述:
 * 线程工具类
 *
 * @author Noah
 * @create 2019-09-26 09:03
 */
public class ThreadUtis {

    public static void threa_2_start_sameDo(Runnable a1, Runnable a2) throws InterruptedException {

        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
