package leetcode;

import java.util.PriorityQueue;

/**
 * 描述:
 * 合并k个有序链表
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * 思路：1、优先级队列(小顶堆)。2、分治思想，两两合并
 *
 * @author Noah
 * @create 2019-11-26 16:44
 */
public class _23 {

    public static void main(String[] args) {
        _23 app = new _23();

        //ListNode head = app.mergeKLists(app.createListNodeArr());
        app.print(app.mergeKLists_v3(app.createListNodeArr()));

    }

    /**
     * ListNode的数组
     *
     * @return
     */
    private ListNode[] createListNodeArr() {
        ListNode[] lists = new ListNode[3];

        ListNode one_1 = new ListNode(1);
        ListNode one_4 = new ListNode(4);
        ListNode one_5 = new ListNode(5);
        one_1.next = one_4;
        one_4.next = one_5;
        lists[0] = one_1;

        ListNode two_1 = new ListNode(1);
        ListNode two_3 = new ListNode(3);
        ListNode two_4 = new ListNode(4);
        two_1.next = two_3;
        two_3.next = two_4;
        lists[1] = two_1;

        ListNode three_2 = new ListNode(2);
        ListNode three_6 = new ListNode(6);
        three_2.next = three_6;
        lists[2] = three_2;

        return lists;
    }

    /**
     * 优先级队列解决方案
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode p = head;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (ListNode listNode : lists) {

            while (listNode != null) {
                priorityQueue.add(listNode.val);
                listNode = listNode.next;
            }
        }

        while (priorityQueue.size() != 0) {
            p.next = new ListNode(priorityQueue.poll());
            p = p.next;
        }

        return head.next;
    }

    /**
     * 自己版本
     * 分治思想：两两合并
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_v2(ListNode[] lists) {

        ListNode head = new ListNode(0);
        ListNode p = head;

        ListNode head2 = mergeTwoLists(lists[0], lists[1]);

        for (int i = 2; i < lists.length; i++) {

        }

        return head;
    }

    /**
     * 参考版：分治算法思想来解决
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_v3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_v2(ListNode l1, ListNode l2) {
        //哨兵，优化代码
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (l1 != null || l2 != null) {

            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;

            if (l1 == null) {
                p.next = l2;
                break;
            }
            if (l2 == null) {
                p.next = l1;
                break;
            }
        }
        return head;
    }

    /**
     * 打印链表
     *
     * @param listNode
     */
    public void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
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
