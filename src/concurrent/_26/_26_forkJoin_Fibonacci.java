package concurrent._26;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 描述:
 * 使用Fork/join分治框架实现斐波那契数列
 *
 * @author Noah
 * @create 2019-10-22 09:11
 */
public class _26_forkJoin_Fibonacci {
    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        Fibonacci fib = new Fibonacci(4);
        Integer r = forkJoinPool.invoke(fib);
        System.out.println("r=" + r);

    }

}

/**
 * 分治递归任务
 */
class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {

        if (n <= 1) {
            return n;
        }

        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);

        return f2.compute() + f1.join();
    }
}
