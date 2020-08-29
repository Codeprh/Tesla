package swordFingerOffer.book;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 两个链表的第一个公共节点
 *
 * @author Noah
 * @create 2020-08-28 8:56 下午
 */
public class p52_getIntersectionNode {

    public static void main(String[] args) {

        ListNode headA = new ListNode(0);
        ListNode headA9 = new ListNode(9);
        ListNode headA1 = new ListNode(1);

        ListNode headB3 = new ListNode(3);

        ListNode head2 = new ListNode(2);
        ListNode head4 = new ListNode(4);

        headA.next = headA9;
        headA9.next = headA1;
        headA1.next = head2;

        headB3.next = head2;

        head2.next = head4;

        p52_getIntersectionNode app = new p52_getIntersectionNode();
        System.out.println(app.getIntersectionNode(headA, headB3));


    }

    /**
     * 寻找两个链表的节点，你走过我的路，我走过你的路，我们一定会相遇
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        ListNode node1 = headA;
        ListNode node2 = headB;

        while (node1 != node2) {
            node1 = (node1 == null) ? headB : node1.next;
            node2 = (node2 == null) ? headA : node2.next;
        }

        return node1;
    }


    /**
     * noah，无解，错误实现：map实现（思考角度是两个相等的值）
     * [4,1,8,4,5], listB = [5,0,1,8,4,5]，结果是要8
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_eror(ListNode headA, ListNode headB) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(headA.val, 1);
        map.put(headB.val, 1);

        while (headA.next != null) {

            ListNode nextA = headA.next;
            ListNode nextB = headB.next;

            if (map.get(nextA.val) != null) {
                return nextA;
            } else {
                map.put(nextA.val, 1);
            }

            if (map.get(nextB.val) != null) {
                return nextB;
            } else {
                map.put(nextB.val, 1);
            }

            headA = headA.next;
            headB = headB.next;

        }

        return null;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
