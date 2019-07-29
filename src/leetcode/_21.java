package leetcode;
//将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 示例：
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
//
//

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Noah
 * @create 2019-07-26 16:06
 */
public class _21 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 试验版：先把两个链表转换为有序数组
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode root = null;
        ListNode other = null;
        List<Integer> list = new ArrayList<>();

        while (l1 != null) {
            list.add(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            list.add(l2.val);
            l2 = l2.next;
        }

        List<Integer> sortList = list.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());

        for (Integer a : sortList) {
            if (root == null) {

                root = new ListNode(a);
                other = root;

            } else {

                ListNode temp = new ListNode(a);
                other.next = temp;
                other = temp;

            }

        }
        return root;
    }

    /**
     * 递归版
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_v2(ListNode l1, ListNode l2) {
        System.out.println("进入了一次+");
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists_v2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_v2(l1, l2.next);
            return l2;
        }

    }

    /**
     * 迭代版本
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_v3(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        _21 app = new _21();
        ListNode node = app.mergeTwoLists_v3(app.node_1(), app.node_2());
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        System.out.println("耗时=" + (System.currentTimeMillis() - start));
    }

    //1->2->4
    private ListNode node_1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node4;
        node4.next = null;
        return node1;
    }

    //    1->3->4
    private ListNode node_2() {
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node3;
        node3.next = node4;
        node4.next = null;
        return node1;
    }
}
