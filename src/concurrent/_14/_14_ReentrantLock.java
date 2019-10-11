package concurrent._14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * 可重入锁
 *
 * @author Noah
 * @create 2019-10-11 09:04
 */
public class _14_ReentrantLock {

    private Lock lock = new ReentrantLock();

    int val;

    public int get() {

        lock.lock();
        try {
            return val;
        } finally {
            lock.unlock();
        }

    }

    public void addOne() {

        lock.lock();

        try {
            val = 1 + get();
        } finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) {

        _14_ReentrantLock app = new _14_ReentrantLock();
        for (int i = 0; i < 100; i++) {
            app.addOne();
        }
        System.out.println("ReentrantLock=" + app.get());

    }
}
