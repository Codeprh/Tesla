package geektimeDatastructureAlgorithm._7;

import geektimeDatastructureAlgorithm._6.SNode;

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
     * 链表反转
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
     * 自己实现（low）
     * 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static SNode merge(SNode list1, SNode list2) {

        SNode f = list1;

        SNode l1head = list1;
        SNode l2head = list2;

        while (l1head.getNext() != null && l2head.getNext() != null) {

            SNode s1 = l1head.getNext();
            SNode s2 = l2head.getNext();

            SNode cur1 = l1head;
            SNode cur2 = l2head;

            if ((Integer) s1.getEle() <= (Integer) s2.getEle()) {

                l1head = l1head.getNext();
                continue;

            } else {
                //我也不知道这块自己写的是什么呢…………
                SNode s2Temp = s2.getNext();
                cur1.setNext(s2);
                l1head = s2;
                l1head.setNext(s1);
                cur2.setNext(s2Temp);
                l2head = cur2;

            }


        }
        //list1再拼接list2后面的元素
        //归并思想：2333
        if (l2head.getNext() != null) {
            l1head.setNext(l2head.getNext());
        }
        return f;

    }

    /**
     * 参考版
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static SNode mergeTwoLists(SNode l1, SNode l2) {
        //利用哨兵结点简化实现难度 技巧三
        SNode soldier = new SNode(0);
        SNode p = soldier;

        while (l1 != null && l2 != null) {
            if ((int) l1.ele < (int) l2.ele) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return soldier.next;
    }

    /**
     * 参考版本
     * 求链表的中间结点
     *
     * @param list
     * @return
     */
    public static SNode findIntermediatenode(SNode list) {

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

        //SNode head = SNode.createIntLinkedListRev();
        //SNode head2 = SNode.createIntLinkedListRev();
        SNode head = createIntLinkedListRev();
        SNode head2 = createIntLinkedListRev();
        SNode.printAll(head);
        SNode.printAll(head2);

        //SNode newHead = reverseLink(head);
        //SNode.printAll(newHead);

        //SNode intermediate = findIntermediatenode(head);
        //System.out.println("中间节点是:" + intermediate.getEle());

        //SNode newHead = delLastKth(head, 2);
        //SNode.printAll(newHead);

        //merge((head), (head2));
        SNode newHead = mergeTwoLists((head), (head2));

        SNode.printAll(newHead);

    }

    /**
     * 创建一个带头链表（正序）
     *
     * @return
     */
    public static SNode createIntLinkedListRev() {

        SNode<Integer> headNull = new SNode(0);

        SNode oneNode = new SNode(1);
        SNode twoNode = new SNode(2);
        SNode threeNode = new SNode(3);
        SNode fourNode = new SNode(4);
        SNode fiveNode = new SNode(5);

        headNull.setNext(oneNode);
        oneNode.setNext(twoNode);
        twoNode.setNext(threeNode);
        threeNode.setNext(fourNode);
        fourNode.setNext(fiveNode);

        return headNull;
    }

}
