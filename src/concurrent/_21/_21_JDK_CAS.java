package concurrent._21;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 * jdk版本实现的cas
 *
 * @author Noah
 * @create 2019-10-17 09:15
 */
public class _21_JDK_CAS {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addOne() {
        atomicInteger.getAndIncrement();
    }
}
