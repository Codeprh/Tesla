package javaOffer._2;

import java.util.concurrent.*;

/**
 * 描述:
 * 子线程返回值栗子
 *
 * @author Noah
 * @create 2020-02-25 07:53
 */
public class ThreadReturnSample {

    static class CycleWait implements Runnable {

        private static String value;

        @Override
        public void run() {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            value = "we have data now";
        }

    }

    static class CallableSample implements Callable<String> {

        @Override
        public String call() throws Exception {

            String value = "test,have data";

            System.out.println("have value data now");

            Thread.sleep(5000);

            System.out.println("have value data now222");

            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //Thread t1 = new Thread(new CycleWait());
        //t1.start();
        //方式一：等待子线程执行完毕
        //t1.join();

        //方式2：循环等待
        // while (CycleWait.value == null) {
        //     Thread.sleep(100);
        // }

        //方式三：通过Callable:FutureTask
        //FutureTask<String> futureTask = new FutureTask<>(new CallableSample());
        //Thread t2 = new Thread(futureTask);
        //t2.start();
        //if (!futureTask.isDone()) {
        //    System.out.println("wait futureTask");
        //}
        //System.out.println("value=" + futureTask.get());

        //方式四：通过Callable：线程池
        ExecutorService ex = Executors.newCachedThreadPool();
        Future<String> stringFuture = ex.submit(new CallableSample());
        System.out.println("value=" + stringFuture.get());
        ex.shutdown();


    }
}
