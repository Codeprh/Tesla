package concurrent._30;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 * 使用threaLocal为每个线程生成唯一id
 *
 * @author Noah
 * @create 2019-10-24 08:58
 */
public class _30_ThreadLocal2Id {

    /**
     * 静态内部类
     */
    static class ThreadId {
        //原子类和threadLocal配合使用
        static final AtomicInteger nextId = new AtomicInteger(0);
        static final ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> nextId.getAndIncrement());

        static Integer get() {
            return tl.get();
        }
    }

    /**
     * 静态线程安全:java.text.SimpleDateFormat
     */
    static class SafeDateFormat {
        static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        static DateFormat get() {
            return tl.get();
        }

    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + ",ThreadId=" + ThreadId.get());

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ",ThreadId=" + ThreadId.get());
        }, "t1");

        thread.start();
    }


}
