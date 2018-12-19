package threadSynchronization.synchronous;

import threadSynchronization.Bank;

/**
 * 描述:
 * 线程同步1：synchronized同步方法，
 *
 * @author codingprh
 * @create 2018-12-18 5:08 PM
 */
public class SynchronizedMethodBank extends Bank {

    @Override
    public synchronized Integer addAccount(Integer money) {
        System.out.println("调用synchronized同步方法");
        return this.account += money;

    }
}