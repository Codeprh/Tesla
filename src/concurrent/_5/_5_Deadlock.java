package concurrent._5;

import concurrent.utils.Allocator;
import concurrent.utils.ThreadUtis;

/**
 * 描述:
 * 转账死锁场景：使用细粒度更小的锁
 *
 * @author Noah
 * @create 2019-09-26 09:02
 */
public class _5_Deadlock {

    public static void main(String[] args) throws InterruptedException {

        UnSalfeAccount a1 = new UnSalfeAccount(1, 200);
        UnSalfeAccount a2 = new UnSalfeAccount(2, 200);

        ThreadUtis.threa_2_start_sameDo(() -> {
            a1.D_PossessionAndWaiting_Transfer(a2, 100);
        }, () -> {
            a2.D_PossessionAndWaiting_Transfer(a1, 100);
        });
        System.out.println("a1=" + a1.getBalance() + ",a2=" + a2.getBalance());
    }

}


/**
 * 转账实体类
 */
class UnSalfeAccount {

    private Integer id;

    private int balance;

    public Integer getBalance() {
        return balance;
    }

    public UnSalfeAccount() {
    }

    public UnSalfeAccount(Integer balance) {
        this.balance = balance;
    }

    /**
     * 经典死锁场景
     *
     * @param target
     * @param amt
     */
    public void transfer(UnSalfeAccount target, Integer amt) {
        //锁定转出账号
        synchronized (this) {
            //锁定转入账号
            synchronized (target) {

                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    /**
     * 解决死锁：破坏占有且等待条件
     */
    public void D_PossessionAndWaiting_Transfer(UnSalfeAccount target, Integer amt) {
        //一次性申请转出账户和转入账户，直到成功
        try {
            Allocator.INSTANCE.apply(this, target);
            transfer(target, amt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Allocator.INSTANCE.free(this, target);
        }
    }

    /**
     * 解决死锁：破坏循环等待
     * <p>
     * 通过业务实现逻辑控制：对资源进行排序，按序申请资源
     */
    public void D_CycleWaiting_Transfer(UnSalfeAccount target, Integer amt) {

        UnSalfeAccount left = this;
        UnSalfeAccount right = target;

        if (left.id > right.id) {
            left = target;
            right = this;
        }

        synchronized (left) {

            synchronized (right) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }

    }


    public UnSalfeAccount(Integer id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}



