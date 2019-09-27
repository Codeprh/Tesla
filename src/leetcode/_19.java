package leetcode;

/**
 * @author Noah
 * @create 2019-08-23 09:20
 */
//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
//
// 示例：
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
//
//
// 说明：
//
// 给定的 n 保证是有效的。
//
// 进阶：
//
// 你能尝试使用一趟扫描实现吗？
//
public class _19 {

    /**
     * 版本1：2次遍历，第一次计算出长度L
     * <p>
     * 第二次删除节点L-N+1
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int len = lenght(head);
        int p = len - n + 1;

        int i = 0;

        while (true) {
            break;
        }
        return null;

    }

    public static void main(String[] args) {
        _19 app = new _19();
        ListNode listNode = app.create();
        app.print(listNode);

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public Integer lenght(ListNode head) {
        int result = 0;
        while (head != null) {
            result++;
            head = head.next;
        }
        return result;

    }

    public void print(ListNode head) {

        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) {
                System.out.print("" + "->");
            }

        }
    }


    public ListNode create() {
        ListNode head = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode five = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = five;
        five.next = null;
        return head;
    }
}
