package swordFingerOffer.book;

/**
 * 描述:链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 * @author Noah
 * @create 2020-09-02 7:15 上午
 */
public class p22_getKthFromEnd {

    /**
     * noah版本：双指针，第一个指针先走了k步，直到第一个为null，第二个指针就是我们返回的结果
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode pre = head;
        ListNode last = head;
        for (int i = 0; i < k; i++) {
            pre = pre.next;
        }
        while (pre != null) {
            pre=pre.next;
            last=last.next;
        }
        return last;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
