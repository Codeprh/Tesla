package concurrent._16;

import java.util.concurrent.Semaphore;

/**
 * 描述:
 * 使用jdk并发包提供的信号量
 *
 * @author Noah
 * @create 2019-10-12 09:10
 */
public class UseSemaphore {

    int count;

    Semaphore s = new Semaphore(1);

    /**
     * 执行count+1操作
     */
    void addOne() {
        try {

            s.acquire();
            count += 1;

        } catch (InterruptedException e) {

            e.printStackTrace();

        } finally {

            s.release();

        }

    }
}
