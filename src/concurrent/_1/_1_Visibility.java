package concurrent._1;

/**
 * 描述:
 * 可见性问题：导致并发bug
 *
 * @author Noah
 * @create 2019-09-23 09:05
 */
public class _1_Visibility {
    private long count = 0;

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final _1_Visibility test = new _1_Visibility();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return test.count;
    }

    public static void main(String[] args) {
        try {
            System.out.println("运算结果=" + _1_Visibility.calc());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
