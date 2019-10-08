package concurrent.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 单例：资源分配器
 * <p>
 * 解决死锁1：破坏占有且等待
 * <p>
 * 类比一个资源管理人员（账本管理人员），一次同时申请转入账号和转出账号
 * <p>
 * 该类必须为单例：只能由一个人来分配资源
 *
 * @author Noah
 * @create 2019-09-26 09:40
 */
public enum Allocator {

    /**
     * 获取到单例对象
     */
    INSTANCE;

    private List<Object> als = new ArrayList<>();

    /**
     * 同时申请多个资源的锁:使用java等待-通知来实现
     *
     * @param lock1
     * @param lock2
     * @return
     */
    public synchronized void apply(Object lock1, Object lock2) {
        while (als.contains(lock1) || als.contains(lock2)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        als.add(lock1);
        als.add(lock2);

    }

    /**
     * 释放资源
     *
     * @param lock1
     * @param lock2
     */
    public synchronized void free(Object lock1, Object lock2) {
        als.remove(lock1);
        als.remove(lock2);
        notifyAll();
    }

    public static Allocator getInstance() {
        return INSTANCE;
    }


}
