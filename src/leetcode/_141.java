package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 描述:
 * 检测链表是否有环
 *
 * @author Noah
 * @create 2019-11-28 16:29
 */
public class _141 {

    public static void main(String[] args) {
        _141 app = new _141();
        ListNode head = app.creatList();
        System.out.println("当前这个链表是否有环=" + app.hasCycle(head));
    }

    /**
     * 最容易实现版本
     * <p>
     * 利用map.contain(key)的特性
     *
     * @param head
     * @return
     */
    public boolean hasCycle_v1(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while (head != null) {

            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 双指针：快慢双指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        ListNode low = head;
        ListNode fast = head.next;

        while (low != fast) {
            if (low == null || fast == null) {
                return false;
            }
            low = low.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 创建链表
     *
     * @return
     */
    public ListNode creatList() {
        ListNode one_1 = new ListNode(1);
        ListNode one_4 = new ListNode(4);
        ListNode one_5 = new ListNode(5);
        one_1.next = one_4;
        one_4.next = one_5;
        one_5.next = one_1;
        return one_1;
    }

    /**
     * 链表定义
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
