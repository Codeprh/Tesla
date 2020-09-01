package swordFingerOffer.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 描述:
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * @author Noah
 * @create 2020-09-01 7:12 上午
 */
public class p6_reversePrint {

    public static void main(String[] args) {

    }



    /**
     * noah版本：使用栈结构
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        ListNode node = head;

        List<Integer> list = new ArrayList<>();

        while ((node) != null) {
            stack.push(node.val);
            node = node.next;
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }

        return list.stream().mapToInt(o -> o).toArray();
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
