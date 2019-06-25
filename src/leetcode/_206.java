package leetcode;

/**
 * @author codingprh
 * @create 2019-06-25 16:41
 */
public class _206 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * // 输入: 1->2->3->4->5->NULL
     * //输出: 5->4->3->2->1->NULL
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        print(head);
        ListNode next = null;
        ListNode prev = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        System.out.println();
        print(prev);
        return prev;
    }

    public void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "-");
            head = head.next;
        }
    }


    public ListNode create() {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        head.next = two;
        two.next = three;
        three.next = null;
        return head;
    }

    public static void main(String[] args) {
        _206 start = new _206();
        start.reverseList(start.create());
    }
}

