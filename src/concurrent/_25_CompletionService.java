package concurrent;

import java.util.concurrent.*;

/**
 * 描述:
 * 批量执行异步任务（最佳实践）
 *
 * @author Noah
 * @create 2019-09-18 17:00
 */
public class _25_CompletionService {


    /**
     * 询价系统 v1
     * <p>
     */
    public void inquiry_v1() {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 异步向电商S1询价
        Future<Integer> f1 = executor.submit(() -> {
            Thread.sleep(9000);
            return 100;
        });
        // 异步向电商S2询价 Future<Integer> f2 =
        Future<Integer> f2 = executor.submit(() -> {
            Thread.sleep(1000);
            return 200;
        });
        // 异步向电商S3询价 Future<Integer> f3 =
        Future<Integer> f3 = executor.submit(() -> {
            Thread.sleep(1000);
            return 300;
        });


        // 获取电商S1报价并异步保存
        executor.execute(() -> {
            try {
                System.out.println("电商报价1：" + f1.get());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        // 获取电商S2报价并异步保存
        executor.execute(() -> {
            try {
                System.out.println("电商报价2：" + f2.get());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        // 获取电商S3报价并异步保存
        executor.execute(() -> {
            try {
                System.out.println("电商报价3：" + f3.get());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("结束了版本");
    }

    /**
     * 询价系统 v2
     */
    public void inquiry_v2() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        CompletionService cs = new ExecutorCompletionService(es, new LinkedBlockingQueue<>(20));
        cs.submit(() -> {
            Thread.sleep(9000);
            return 100;
        });
        cs.submit(() -> {
            Thread.sleep(1000);
            return 200;
        });
        cs.submit(() -> {
            Thread.sleep(1000);
            return 300;
        });
        for (int i = 0; i < 3; i++) {
            try {
                Integer r = (int) cs.take().get();
                es.execute(() -> {
                    System.out.println("报错报价" + r);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        _25_CompletionService app = new _25_CompletionService();
        app.inquiry_v1();
        System.out.println("结束了主流程");
    }
}
