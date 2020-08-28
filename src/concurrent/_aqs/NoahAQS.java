package concurrent._aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 描述:
 * 自定义同步器
 *
 * @author Noah
 * @create 2020-06-30 10:00
 */
public class NoahAQS extends AbstractQueuedSynchronizer {
    protected NoahAQS() {
        super();
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
