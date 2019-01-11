package threadCommunication;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * 描述:
 * 双重锁线程安全单例模式
 *
 * @author codingprh
 * @create 2019-01-11 9:01 AM
 */
public class DoubleCheckedSingthonThreadPool {

    private static final int poolSize = Runtime.getRuntime().availableProcessors() * 2;
    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
    private static final RejectedExecutionHandler policy = new ThreadPoolExecutor.DiscardPolicy();
    private static final ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize, 0, TimeUnit.SECONDS, queue, policy);

    private static volatile DoubleCheckedSingthonThreadPool installce;


    private DoubleCheckedSingthonThreadPool() {
        if (installce != null) {
            throw new IllegalStateException("已经初始化了");
        }

    }

    public static DoubleCheckedSingthonThreadPool getInstallce() {

        DoubleCheckedSingthonThreadPool result = installce;

        if (result == null) {

            synchronized (DoubleCheckedSingthonThreadPool.class) {

                result = installce;

                if (Objects.isNull(result)) {
                    installce = result = new DoubleCheckedSingthonThreadPool();
                }
            }
        }
        return result;
    }

    public void run(Runnable r) {
        executorService.submit(r);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}