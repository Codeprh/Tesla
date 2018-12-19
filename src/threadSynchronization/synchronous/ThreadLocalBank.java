package threadSynchronization.synchronous;

/**
 * 描述:
 * ThreadLocal局部变量实现线程同步
 *
 * @author codingprh
 * @create 2018-12-19 10:30 AM
 */
public class ThreadLocalBank {
    private ThreadLocal<Integer> account = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public Integer addAccount(Integer money) {

        account.set(account.get() + money);
        return account.get();

    }

    public Integer getAccount() {
        return account.get();
    }

}