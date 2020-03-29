package javaOffer._2;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 * countDownLatch栗子
 *
 * @author Noah
 * @create 2020-03-02 13:11
 */
public class CountDownLatchSample {

    static class TaskPortion implements Runnable {

        private static int counter = 0;
        private final int id = counter++;
        private static Random rand = new Random(47);
        private final CountDownLatch latch;
        private static List<Integer> safeList = Collections.synchronizedList(new ArrayList<>());
        private static List<Integer> safeCountList = Collections.synchronizedList(new ArrayList<>());

        public TaskPortion(CountDownLatch latch) {
            this.latch = latch;
            safeList.add(id);
            safeCountList.add(counter);
        }

        @Override

        public void run() {
            try {
                doWork();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }

        private void doWork() {
            try {

                if (safeList.size() == 100) {
                    Object[] objects = safeCountList.toArray();
                    Arrays.sort(objects);
                    System.out.println(Arrays.toString(objects));
                }

                //TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
                System.out.println(this + ",completed");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "TaskPortion{" +
                    "id=" + id +
                    ", latch=" + latch +
                    '}';
        }
    }

    static class WaitingTask implements Runnable {

        private static int counter = 0;
        private final int id = counter++;
        private final CountDownLatch latch;

        public WaitingTask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
                System.out.println("latch barrier passed for " + this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public String toString() {
            return "WaitingTask{" +
                    "id=" + id +
                    ", latch=" + latch +
                    '}';
        }
    }

    public static void main(String[] args) {
        final int SIZE = 100;
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);

        for (int i = 0; i < 10; i++) {
            exec.execute(new WaitingTask(latch));
        }

        for (int i = 0; i < SIZE; i++) {
            exec.execute(new TaskPortion(latch));
        }

        System.out.println("launch all tasks");
        exec.shutdown();

    }
}
