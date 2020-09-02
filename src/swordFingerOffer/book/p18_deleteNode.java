package swordFingerOffer.book;

/**
 * 描述:
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 *
 * @author Noah
 * @create 2020-09-01 8:34 上午
 */
public class p18_deleteNode {

    /**
     * noah版本：不再低调
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {

        ListNode root = head;

        if (head.val == val) {
            return head.next;
        }

        while (root != null) {

            if (root.next == null) {
                return head;
            }

            if (root.next.val == val) {
                root.next = root.next.next;
            } else {
                root = root.next;
            }

        }

        return head;
    }


    /**
     * 递归版本
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode_right(ListNode head, int val) {
        if (head == null)
            return head;
        if (head.val == val)
            return head.next;
        head.next = deleteNode_right(head.next, val);
        return head;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
