package concurrent._16;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 描述:
 * 限流器
 * <p>
 * 使用信号量实现限流器：要解决的问题，有一组对象，能够在池中被反复使用。
 * <p>
 * 这些对象要共同访问临界区，因此只能使用信号量来实现限流
 *
 * @author Noah
 * @create 2019-10-12 09:26
 */

public class ObjPool<T, R> {

    final List<T> pool;
    final Semaphore semaphore;

    public ObjPool(T t, int size) {

        pool = new Vector<>();
        for (; size > 0; size--) {
            pool.add(t);
        }
        //限流池中的对象只有一半能够访问临界区
        semaphore = new Semaphore(size);

    }

    public R exec(Function<T, R> func) throws InterruptedException {

        T t = null;

        try {

            semaphore.acquire();
            t = pool.remove(0);
            return func.apply(t);

        } finally {
            pool.add(t);
            semaphore.release();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ObjPool pool = new ObjPool(2, 10);
        pool.exec(t -> {
            System.out.println(t);
            return t.toString();
        });

    }
}


