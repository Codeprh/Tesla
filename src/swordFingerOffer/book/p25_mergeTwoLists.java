package swordFingerOffer.book;

/**
 * 描述:
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * @author Noah
 * @create 2020-09-02 7:34 上午
 */
public class p25_mergeTwoLists {

    /**
     * 伪头节点：直接撸版本：需要注意临界值的判断，
     * 从两个链表摘星星
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_right(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;

    }

    /**
     * noah版本：需要自己输出一遍
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
