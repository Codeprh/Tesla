package concurrent._18;

import java.util.concurrent.locks.StampedLock;

/**
 * 描述:
 * 比读写锁（ReadWriteLock）更好的性能
 *
 * @author Noah
 * @create 2019-10-14 09:16
 */
public class _18_StampedLock {

    final StampedLock stampedLock = new StampedLock();

    int x;
    int y;

    /**
     * 悲观读操作
     */
    public void read() {
        long stamp = stampedLock.readLock();
        try {
            //执行业务
            System.out.println("");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    /**
     * 乐观读栗子
     */
    public void optimisticRead() {

        long stamp = stampedLock.tryOptimisticRead();

        //乐观读是无锁的，把成员变量读入到局部变量,非线程安全的。
        int curX = x;
        int curY = y;

        //校验：在执行读的过程是否有写操作，有的话就升级为悲观读
        if (!stampedLock.validate(stamp)) {

            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }

        }

    }

    /**
     * 写操作
     */
    public void write() {
        long stamp = stampedLock.writeLock();

        try {
            //执行业务
            System.out.println("");
        } finally {
            stampedLock.unlockWrite(stamp);
        }

    }

    public static void main(String[] args) {

    }
}
