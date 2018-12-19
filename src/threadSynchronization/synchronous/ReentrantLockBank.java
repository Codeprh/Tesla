package threadSynchronization.synchronous;

import threadSynchronization.Bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * 重入锁bank
 *
 * @author codingprh
 * @create 2018-12-18 6:03 PM
 */
public class ReentrantLockBank extends Bank {

    private Lock lock = new ReentrantLock();

    @Override
    public Integer addAccount(Integer money) {
        lock.lock();
        try {
            return this.account += money;
        } finally {
            lock.unlock();
        }
    }
}