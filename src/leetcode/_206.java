package leetcode;

/**
 * 反转链表
 *
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
     * <p>
     * 假设存在链表 1 → 2 → 3 → Ø，我们想要把它改成 Ø ← 1 ← 2 ← 3。
     * <p>
     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * <p>
     * 在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        print(head);

        ListNode next = null;
        ListNode prev = null;

        while (head != null) {
            //在更改引用之前，还需要另一个指针来存储下一个节点
            next = head.next;
            //将当前节点的 next 指针改为指向前一个元素
            head.next = prev;
            prev = head;
            //不要忘记在最后返回新的头引用
            head = next;
        }
        print(prev);
        return prev;
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

