package noahjdk;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 描述:
 * 测试
 *
 * @author Noah
 * @create 2020-04-18 14:24
 */
public class StringAndSout {

    public static final ExecutorService es = Executors.newFixedThreadPool(8);

    public static final int count = 5000;

    public static void main(String[] args) throws InterruptedException {
        //singleFor();

        StopWatch stopWatch = new StopWatch();

        stopWatch.start("测试sout的耗时");

        Thread t1 = new Thread(() -> {
            multithreadingFor();
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            multithreadingFor();
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            multithreadingFor();
        }, "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            multithreadingFor();
        }, "t4");
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        stopWatch.stop();
        System.out.println(es.toString());
        es.shutdown();
        System.out.println("耗时=" + stopWatch.getTotalTimeSeconds() + "s");

    }

    //四个任务&&sout，12s   no sout 3s
    public static void multithreadingFor() {

        Future<?> submit = es.submit(() -> {
            for (int i = 0; i < count; i++) {
                String a = UUID.randomUUID().toString();
                System.out.println(a);
            }
        });

        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 偏向锁
     * 单线程执行一次任务耗时:3.680956976s
     */
    public static void singleFor() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("测试sout的耗时");

        for (int i = 0; i < count; i++) {
            String a = UUID.randomUUID().toString();
            System.out.println(a);
        }

        stopWatch.stop();
        System.out.println("耗时=" + stopWatch.getTotalTimeSeconds() + "s");
    }

}
