package threadSynchronization.synchronous;

/**
 * 描述:
 * 同步方式3：使用volatile关键词
 *
 * @author codingprh
 * @create 2018-12-18 5:35 PM
 */
public class VolatileBank {
    private volatile Integer account = 0;


    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public synchronized Integer addAccount(Integer money) {
        return this.account += money;
    }
}