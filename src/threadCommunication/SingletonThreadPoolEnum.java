package threadCommunication;

import java.util.concurrent.*;

/**
 * 使用单例模式实现线程池的访问方式
 *
 * @author codingprh
 * @create 2018-12-20 8:48 AM
 */
public enum SingletonThreadPoolEnum {
    INSTANCE;

    private static final int poolSize = Runtime.getRuntime().availableProcessors() * 2;
    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
    private static final RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
    private static final ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);

    public void run(Runnable r) {
        executorService.submit(r);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
