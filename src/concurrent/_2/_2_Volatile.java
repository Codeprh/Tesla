package concurrent._2;

/**
 * 描述:
 * volatile解决可见性问题
 *
 * @author Noah
 * @create 2019-09-24 09:15
 */
public class _2_Volatile {

    int x = 0;
    volatile boolean v = false;

    public void write() {
        x = 44;
        v = true;
    }

    public void read() {
        if (v == true) {
            System.out.println("x=" + x);
        }
    }

    public static void startAll() throws InterruptedException {

        final _2_Volatile app = new _2_Volatile();

        Thread thread1 = new Thread(() -> {
            app.read();
        });
        Thread thread2 = new Thread(() -> {
            app.write();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    public static void main(String[] args) {
        try {
            startAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
