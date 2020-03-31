package noahjdk;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 * ThreadLocal栗子
 *
 * @author Noah
 * @create 2020-02-26 21:25
 */
public class ThreadLocalSample {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("curreant thread=" + Thread.currentThread().getName() + ",ThreadId=" + ThreadLocalSample.get());
                }

            }).start();
        }

        Map<String, Integer> map = new HashMap<>();

    }
}
