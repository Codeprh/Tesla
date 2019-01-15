package oop_myLinkedList;

/**
 * 描述:
 * 链表的节点
 *
 * @author codingprh
 * @create 2019-01-12 7:53 AM
 */
public class MyNode<T> {

    private final T value;
    private MyNode<T> next;

    public MyNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }


    public MyNode<T> getNext() {
        return next;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }

    /**
     * demo printListNode
     * @param head
     * @param <T>
     */
    public static <T> void printListNode(MyNode<T> head) {
        while (head != null) {
            System.out.println(head.getValue());
            head = head.getNext();
        }

    }
}