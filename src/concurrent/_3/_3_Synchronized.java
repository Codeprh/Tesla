package concurrent._3;

/**
 * 描述:
 * synchronized关键字的使用
 *
 * @author Noah
 * @create 2019-09-24 22:39
 */
public class _3_Synchronized {
    long val = 0L;

    public synchronized long getVal() {
        return val;
    }

    public synchronized void addOne() {
        int idx = 0;
        while (idx++ < 10000) {
            val += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final _3_Synchronized test = new _3_Synchronized();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(() -> {
            test.addOne();
        });
        Thread th2 = new Thread(() -> {
            test.addOne();

        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return test.getVal();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("计算结果=" + calc());

    }

}
