package swordFingerOffer.book;

/**
 * 描述:
 * 反转链表
 *
 * @author Noah
 * @create 2020-09-02 7:24 上午
 */
public class p24_reverseList {

    public static void main(String[] args) {

    }

    /**
     * noah版本：反转链表，思路清晰
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode root = head;
        ListNode pre = head;
        ListNode nullNode = null;

        while (pre != null) {

            ListNode next = pre.next;
            pre.next = nullNode;

            nullNode = pre;
            pre = next;

        }

        return nullNode;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
