package geektime_datastructure_algorithm._6;

/**
 * 描述:
 * 抽取通用的链表结构
 * <p>
 * 单链表
 *
 * @author Noah
 * @create 2019-10-29 09:51
 */
public class SNode<T> {
    private T ele;
    private SNode next;

    public SNode() {
        this.next = null;
    }

    public SNode(T ele, SNode next) {
        this.ele = ele;
        this.next = next;
    }

    public T getEle() {
        return ele;
    }

    public void setEle(T ele) {
        this.ele = ele;
    }

    public SNode getNext() {
        return next;
    }

    public void setNext(SNode next) {
        this.next = next;
    }
}
