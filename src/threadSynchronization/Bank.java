package threadSynchronization;

/**
 * 描述:
 * 银行基础加钱
 *
 * @author codingprh
 * @create 2018-12-18 3:35 PM
 */

public class Bank {
    protected Integer account = 0;

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer addAccount(Integer money) {
        return account += money;
    }
}