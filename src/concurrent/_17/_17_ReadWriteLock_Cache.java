package concurrent._17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述:
 * 使用读写锁来实现缓存
 *
 * @author Noah
 * @create 2019-10-13 20:55
 */
public class _17_ReadWriteLock_Cache<K, V> {

    /**
     * 存储数据的载体：非线程安全
     */
    final Map<K, V> data = new HashMap<>();

    /**
     * 可重入读写锁
     */
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * 读锁
     */
    final Lock rl = rwl.readLock();

    /**
     * 写锁
     */
    final Lock wl = rwl.writeLock();

    /**
     * 读缓存操作：
     * <p>
     * 按需加载
     *
     * @param key
     * @return
     */
    public V getValue(K key) {

        V val = null;

        rl.lock();
        try {
            val = data.get(key);
        } finally {
            rl.unlock();
        }

        if (val != null) {
            return val;
        }

        wl.lock();

        try {
            //再次校验：可能其他线程已经获取到了数据
            val = data.get(key);

            if (val == null) {
                //查询数据写入缓存场景
                data.put(key, val);
            }

        } finally {
            wl.unlock();
        }
        return val;
    }

    /**
     * 读锁升级为写锁：不能实现
     * <p>
     * 写锁降级为读锁：可以实现
     *
     * @param key
     * @return
     */
    public V get(K key) {

        rl.lock();
        V r = null;

        try {
            r = data.get(key);
        } finally {
            rl.unlock();
        }

        if (r == null) {

            try {
                wl.lock();

                if ((r = data.get(key)) != null) {
                    return r;
                }

                //让缓存中写入数据
                data.put(key, r);

                rl.lock();

            } finally {
                wl.unlock();
            }

        }
        try {
            //读锁仍然持有
            data.size();
        } finally {
            rl.unlock();
        }
        return r;
    }

    /**
     * 写缓存操作
     *
     * @param k
     * @param v
     */
    public void writeValue(K k, V v) {
        wl.lock();
        try {
            data.put(k, v);
        } finally {
            wl.unlock();
        }
    }

}
