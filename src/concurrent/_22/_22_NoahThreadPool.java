package concurrent._22;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:
 * noah自定义线程池的实现
 *
 * @author Noah
 * @create 2019-10-17 10:05
 */
public class _22_NoahThreadPool {

    /**
     * 阻塞队列
     */
    private BlockingQueue<Runnable> blockingQueue;

    /**
     * 工作线程
     */
    private List<NoahThreadPool> threadPools = new ArrayList<>();

    /**
     * 构造线程池
     *
     * @param queue
     * @param poolSize
     */
    public _22_NoahThreadPool(BlockingQueue<Runnable> queue, int poolSize) {

        this.blockingQueue = queue;
        for (; poolSize > 0; poolSize--) {
            NoahThreadPool thread = new NoahThreadPool();
            thread.start();
            threadPools.add(thread);
        }

    }

    /**
     * 暴露外部任务接口
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        blockingQueue.add(runnable);
    }

    class NoahThreadPool extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = blockingQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //_22_NoahThreadPool noahThreadPool = new _22_NoahThreadPool(new LinkedBlockingQueue<>(20), 100);
        ExecutorService singleThread = Executors.newFixedThreadPool(300);

        for (int i = 100; i > 0; i--) {
            singleThread.execute(() -> {
                System.out.println("当前线程名=" + Thread.currentThread().getName());
            });
            Thread.sleep(500);
        }
    }
}
