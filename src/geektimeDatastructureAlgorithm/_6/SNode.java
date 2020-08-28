package geektimeDatastructureAlgorithm._6;

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

    public T ele;
    public SNode next;

    public SNode() {
        this.next = null;
    }

    public SNode(T ele, SNode next) {
        this.ele = ele;
        this.next = next;
    }

    public SNode(T ele) {
        this.ele = ele;
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

    /**
     * 创建一个带头链表(倒序)
     *
     * @return
     */
    public static SNode createIntLinkedList() {

        SNode<Integer> head = new SNode();
        SNode node = head.getNext();

        SNode oneNode = new SNode(1, node);
        head.setNext(oneNode);

        SNode twoNode = new SNode(2, oneNode);
        head.setNext(twoNode);

        SNode threeNode = new SNode(3, twoNode);
        head.setNext(threeNode);

        SNode fourNode = new SNode(4, threeNode);
        head.setNext(fourNode);

        SNode fiveNode = new SNode(5, fourNode);
        head.setNext(fiveNode);

        return head;
    }

    /**
     * 创建一个带头链表（正序）
     *
     * @return
     */
    public static SNode createIntLinkedListRev() {

        SNode<Integer> headNull = new SNode();

        SNode oneNode = new SNode(1);
        SNode twoNode = new SNode(2);
        SNode threeNode = new SNode(8);
        SNode fourNode = new SNode(12);
        SNode fiveNode = new SNode(19);

        headNull.setNext(oneNode);
        oneNode.setNext(twoNode);
        twoNode.setNext(threeNode);
        threeNode.setNext(fourNode);
        fourNode.setNext(fiveNode);

        return headNull;
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public static void printAll(SNode head) {
        SNode node = head;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.getEle());
            sb.append(" ");
            node = node.getNext();
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {


        SNode head = createIntLinkedList();


    }
}
