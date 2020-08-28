package geektimeDatastructureAlgorithm._6;

import java.util.Objects;

/**
 * 描述:
 * todo:考虑哨兵的双向链表（循环）
 * 基于链表实现LRU缓存淘汰算法
 *
 * @author Noah
 * @create 2019-10-28 10:10
 */
public class _6_LRU_BaseLinkedList<T> {

    public static final int DEFAULT_CAPACITY = 1 << 3;

    private Integer capacity;

    private int count;

    //哨兵：头节点链表
    private SNode<T> headNode;

    public _6_LRU_BaseLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    public _6_LRU_BaseLinkedList(Integer capacity) {
        this.capacity = capacity;
        this.headNode = new SNode<>();
    }

    /**
     * 访问元素
     *
     * @param obj
     */
    public void offer(T obj) {

        SNode node = findPreNode(obj);

        //不存在链表中
        if (node == null) {

            //达到了链表的最大容量
            if (Objects.equals(count, capacity)) {
                removeLast();
                insertInBegin(obj);
            } else {
                //插入到头部
                insertInBegin(obj);
            }

        } else {
            //存在链表中，node是要删除节点的前驱节点
            delNodeNext(node);
            insertInBegin(obj);


        }
    }

    /**
     * 删除尾节点
     * <p>
     * 直接找到倒数第二个节点
     * <p>
     * todo：考虑循环链表的实现
     */
    public void removeLast() {

        SNode node = headNode;

        while (node.getNext().getNext() != null) {
            node = node.getNext();
        }

        SNode delNode = node.getNext();
        node.setNext(null);
        delNode = null;
        this.count--;

    }


    /**
     * 删除当前节点的next节点
     *
     * @param node
     */
    public void delNodeNext(SNode node) {

        SNode temp = node.getNext();
        node.setNext(temp.getNext());

        //GcRoot，便于gc
        temp = null;
        this.count--;
    }

    /**
     * 在头部插入数据
     *
     * @param data
     */
    public void insertInBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        this.count++;
    }

    /**
     * 查找元素是否在链表中。
     * <p>
     * 如果存在返回该节点的前驱节点。
     *
     * @param obj
     * @return
     */
    public SNode findPreNode(T obj) {
        SNode node = headNode;
        while (node.getNext() != null) {

            if (Objects.equals(node.getNext().getEle(), obj)) {
                return node;
            }
            node = node.getNext();

        }
        return null;
    }

    @Override
    public String toString() {

        SNode node = headNode;
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.getEle());
            sb.append(" ");
            node = node.getNext();
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        _6_LRU_BaseLinkedList<Integer> app = new _6_LRU_BaseLinkedList<>(2);
        app.offer(1);
        app.offer(2);
        app.offer(3);
        System.out.println(app.toString());
    }
}
