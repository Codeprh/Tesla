package concurrent._4;

/**
 * 描述:
 * 一把锁多个关联的资源
 *
 * @author Noah
 * @create 2019-09-25 00:09
 */
public class _4_oneLock_2_related_resouce {

    public void threa_2_start_sameDo(Runnable a1, Runnable a2) throws InterruptedException {

        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(a2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

    public static void main(String[] args) throws InterruptedException {

        UnSalfeAccount a1 = new UnSalfeAccount(200);
        UnSalfeAccount a2 = new UnSalfeAccount(200);
        UnSalfeAccount a3 = new UnSalfeAccount(200);

        _4_oneLock_2_related_resouce app = new _4_oneLock_2_related_resouce();

        app.threa_2_start_sameDo(() -> {
            int index = 0;
            a1.transfer(a2, 100);


        }, () -> {
            int index = 0;
            a2.transfer(a3, 100);

        });

        System.out.println("a1=" + a1.getBalance() + ",a2=" + a2.getBalance() + ",a3=" + a3.getBalance());
    }
}

/**
 * 不安全的转账账号
 */
class UnSalfeAccount {

    private int balance;

    public Integer getBalance() {
        return balance;
    }

    public UnSalfeAccount() {
    }

    public UnSalfeAccount(Integer balance) {
        this.balance = balance;
    }

    public void transfer(UnSalfeAccount target, Integer amt) {
        //如果改为类级别锁，那如果线程安全的。但是所有的转账操作都串行化的。
        synchronized (this) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
