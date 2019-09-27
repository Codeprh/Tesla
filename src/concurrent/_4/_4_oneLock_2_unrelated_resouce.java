package concurrent._4;

/**
 * 描述:
 * 用一把锁保护多个没有关联的资源
 *
 * @author Noah
 * @create 2019-09-24 23:10
 */
public class _4_oneLock_2_unrelated_resouce {

    private Integer balance;
    private String password;

    /**
     * 锁：保护余额
     */
    public static final Object balLock = new Object();
    /**
     * 锁：保护密码
     */
    public static final Object pwLock = new Object();

    /**
     * 取款
     */
    public void withdraw(Integer amt) {
        synchronized (balLock) {
            if (this.balance > amt) {
                this.balance -= amt;
            }
        }
    }

    /**
     * 查询余额
     *
     * @return
     */
    public Integer getBalance() {
        synchronized (balLock) {
            return balance;
        }
    }

    /**
     * 更改密码
     *
     * @param pw
     */
    public void changePw(String pw) {
        synchronized (pwLock) {
            this.password = pw;
        }
    }

    public String getPassword() {
        synchronized (pwLock) {
            return this.password;
        }
    }


    public static void main(String[] args) {

    }
}
