package geektime_datastructure_algorithm._7;

import geektime_datastructure_algorithm._6.SNode;

/**
 * 描述:
 * <p>
 * 链表题目终结者：
 * <p>
 * - 单链表反转
 * - 链表中环的检测
 * - 两个有序的链表合并
 * - 删除链表倒数第n个结点
 * - 求链表的中间结点
 *
 * @author Noah
 * @create 2019-10-29 18:10
 */
public class _LinkedList_All {

    /**
     * 单链表反转：脑壳疼，胸口闷。
     */
    public static SNode reverseLink(SNode headNode) {

        if (headNode == null) {
            return null;
        }

        SNode curr = headNode;
        SNode pre = null;

        while (curr != null) {

            SNode temp = curr.getNext();
            curr.setNext(pre);

            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 参考版本
     *
     * @param list
     * @return
     */
    public static SNode reverse(SNode list) {
        SNode curr = list, pre = null;
        while (curr != null) {
            SNode next = curr.getNext();
            curr.setNext(pre);
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 参考版本
     * 单链表删除倒数第K个节点
     *
     * @param list
     * @param k
     * @return
     */
    public static SNode delLastKth(SNode list, int k) {
        SNode fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.getNext();
            ++i;
        }

        if (fast == null) return list;

        SNode slow = list;
        SNode prev = null;
        while (fast.getNext() != null) {
            fast = fast.getNext();
            prev = slow;
            slow = slow.getNext();
        }

        if (prev == null) {
            list = list.getNext();
        } else {
            prev.setNext(prev.getNext().getNext());
        }
        return list;

    }

    /**
     * 参考版本
     * 求链表的中间结点
     *
     * @param list
     * @return
     */
    public static SNode findIntermediatenode(SNode list) {

        //偶数和奇数的情况:最右情况

        if (list == null) return null;

        SNode fast = list;
        SNode slow = list;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;
    }


    public static void main(String[] args) {

        SNode head = SNode.createIntLinkedList();
        SNode.printAll(head);

        //SNode newHead = reverseLink(head);
        //SNode.printAll(newHead);

        //SNode intermediate = findIntermediatenode(head);
        //System.out.println("中间节点是:" + intermediate.getEle());

        SNode newHead = delLastKth(head, 2);
        SNode.printAll(newHead);

    }

}
