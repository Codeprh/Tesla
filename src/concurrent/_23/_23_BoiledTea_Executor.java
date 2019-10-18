package concurrent._23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 描述:
 * 烧水泡茶Executor实现
 * <p>
 * T1负责洗水壶、烧开水、泡茶这三道工序，
 * T2负责洗茶壶、洗茶杯、拿茶叶三道工序，
 * <p>
 * 其中T1 在执行泡茶这道工序时需要等待T2完成拿茶叶的工序。
 * <p>
 * Thread.join()、CountDownLatch，甚至阻塞队列都可以解决，不过今天我们用Future特性来实 现。
 *
 * @author Noah
 * @create 2019-10-17 17:22
 */
public class _23_BoiledTea_Executor {

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * 执行任务
     */
    public void runTask() {

        Future f2 = executorService.submit(() -> {
            System.out.println("洗茶壶");
            Thread.sleep(1000);
            System.out.println("洗茶杯");
            System.out.println("拿茶叶");
            return "success";
        });

        executorService.submit(() -> {

            try {
                System.out.println("洗水壶");
                System.out.println("烧开水");
                String status = (String) f2.get();
                System.out.println("获取到状态=" + status);
                System.out.println("泡茶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        });
    }

    public static void main(String[] args) {
        _23_BoiledTea_Executor app = new _23_BoiledTea_Executor();
        app.runTask();
    }

}
