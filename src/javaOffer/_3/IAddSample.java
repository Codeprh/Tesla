package javaOffer._3;

/**
 * 描述:
 * voltile，i++查看字节码
 *
 * @author Noah
 * @create 2020-03-29 17:07
 */
public class IAddSample {
    private volatile int i;

    public void add() {
        i++;
    }
}
