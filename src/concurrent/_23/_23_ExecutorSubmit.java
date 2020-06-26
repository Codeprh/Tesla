package concurrent._23;

import java.util.concurrent.*;

/**
 * 描述:
 * 线程池执行任务获取结果
 *
 * @author Noah
 * @create 2019-10-17 11:26
 */
public class _23_ExecutorSubmit {


    /**
     * 定义线程池
     */
    ExecutorService executor = Executors.newFixedThreadPool(2);

    public void useSubmit() throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            return "hello futureTask";
        });

        //Runnable
        Future f1 = executor.submit(() -> {
            System.out.println("hello Runnable,no return");
        });

        //Callable
        Future f2 = executor.submit(() -> {

            System.out.println("hello Callable,have return");

            return "hello Callable";
        });
        //Runnable + Result方式
        Future f3 = executor.submit(() -> {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello Runnable,with return");
        }, "hello Runnable");

        //futureTask方式
        executor.submit(futureTask);

        //等价Thread.join()
        Object r1 = f1.get();

        String r2 = (String) f2.get();

        String r3 = (String) f3.get();

        String r4 = futureTask.get();

        Thread.sleep(1000);

        System.out.println("r1=" + r1 + ",r2=" + r2 + ",r3=" + r3 + ",r4=" + r4);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        _23_ExecutorSubmit executorSubmit = new _23_ExecutorSubmit();
        executorSubmit.useSubmit();
    }
}
