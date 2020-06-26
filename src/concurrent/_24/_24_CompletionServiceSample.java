package concurrent._24;

import java.util.concurrent.*;

/**
 * 描述:
 * 异步执行多个任务.
 * <p>
 * 需求：
 * 不使用求和公式，计算从1到100000000相加的和.
 * <p>
 * 当向Executor提交多个任务并且希望获得它们在完成之后的结果，如果用FutureTask，可以循环获取task，并调用get方法去获取task执行结果，但是如果task还未完成，
 * 获取结果的线程将阻塞直到task完成，由于不知道哪个task优先执行完毕，使用这种方式效率不会很高。
 * 在jdk5时候提出接口CompletionService，它整合了Executor和BlockingQueue的功能，可以更加方便在多个任务执行时获取到任务执行结果。
 *
 * @author Noah
 * @create 2020-04-08 10:49
 */
public class _24_CompletionServiceSample {

    private static ExecutorService ex = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {
        singleThreadTest();
        comletionServiceTest();

    }

    /**
     * 单线程计算
     */
    public static void singleThreadTest() {
        long r = 0;
        for (int i = 1; i <= 100000000; i++) {
            r += i;
        }
        System.out.println("1+……+100000000,结果=" + r);

    }

    /**
     * comletionService实现累加计算
     */
    public static void comletionServiceTest() {

        CompletionService<Long> completionService = new ExecutorCompletionService<>(ex);
        final int groupNum = 100000000 / 100;

        for (int i = 1; i <= 100; i++) {

            int start = (i - 1) * groupNum + 1;
            int end = i * groupNum;

            completionService.submit(() -> {
                //printThreadPool();
                long sum = 0;
                for (int j = start; j <= end; j++) {
                    sum += j;
                }
                return sum;
            });
        }

        long r = 0;

        try {
            for (int i = 0; i < 100; i++) {
                r += completionService.take().get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ex.shutdown();

        System.out.println("1+……+100000000,结果=" + r);
    }

    public static void printThreadPool() {

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) ex;

        System.out.println("核心线程数=" + tpe.getCorePoolSize());
        System.out.println("最大线程数=" + tpe.getMaximumPoolSize());
        System.out.println("队列大小=" + tpe.getQueue().size());
        System.out.println("================");

    }
}
