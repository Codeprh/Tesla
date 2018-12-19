package threadSynchronization.synchronous;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 * 原子变量实现同步
 *
 * @author codingprh
 * @create 2018-12-20 7:56 AM
 */
public class AtomicBank {
    private AtomicInteger account = new AtomicInteger(0);

    public AtomicInteger getAccount() {
        return account;
    }

    public void setAccount(AtomicInteger account) {
        this.account = account;
    }

    public Integer addAccount(Integer money) {
        return account.addAndGet(money);
    }
}