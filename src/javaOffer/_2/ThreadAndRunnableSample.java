package javaOffer._2;

/**
 * 描述:
 * Thread和Runnable的栗子
 *
 * @author Noah
 * @create 2020-02-25 07:36
 */
public class ThreadAndRunnableSample {

    /**
     * 实现多线程方式一：extends Thead
     */
    static class ThreadSample extends Thread {

        private String name;

        public ThreadSample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("current thread name = " + this.name + ",i=" + i);
            }
        }
    }

    static class RunnableSample implements Runnable {

        private String name;

        public RunnableSample(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("current thread name = " + this.name + ",i=" + i);
            }
        }
    }


    public static void main(String[] args) {
        //测试Thread
        ThreadSample t1 = new ThreadSample("t1");
        ThreadSample t2 = new ThreadSample("t2");
        ThreadSample t3 = new ThreadSample("t3");

        t1.start();
        t2.start();
        t3.start();

        //测试runnable
        Thread r1 = new Thread(new RunnableSample("r1"));
        Thread r2 = new Thread(new RunnableSample("r2"));
        Thread r3 = new Thread(new RunnableSample("r3"));

        r1.start();
        r2.start();
        r3.start();

    }
}
