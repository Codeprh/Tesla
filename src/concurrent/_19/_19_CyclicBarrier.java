package concurrent._19;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 描述:
 * 线程同步的高级方式：java.util.concurrent.CyclicBarrier
 *
 * @author Noah
 * @create 2019-10-15 09:19
 */
public class _19_CyclicBarrier<P, D> {

    /**
     * 对账单线程
     */
    final Executor executorService = Executors.newFixedThreadPool(1);

    /**
     * 队列线程
     */
    final Executor queueExecutor = Executors.newFixedThreadPool(2);
    
    /**
     * 同步协调两个队列
     */
    final CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        executorService.execute(() -> check());

    });

    /**
     * 订单队列
     */
    Vector<P> pos;

    /**
     * 派单队列
     */
    Vector<D> dos;

    /**
     * 执行对账流程
     */
    public void check() {
        P p = pos.remove(0);
        D d = dos.remove(0);
        //执行对账逻辑
    }

    /**
     * 查询未派单的队列
     */
    public void checkAll() {

        queueExecutor.execute(() -> {
            try {
                //查询未对账的订单
                pos.add(null);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        queueExecutor.execute(() -> {
            try {
                //查询未对账的派单
                dos.add(null);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

    }

}
