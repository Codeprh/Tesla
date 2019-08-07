package leetcode;

/**
 * @author Noah
 * @create 2019-08-01 18:07
 */
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
//
public class _2 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 第一个版本：暴力破解法
     * <p>
     * 无效，当数值太大了，远远大于Long.MaxVal的时候，只能自己模拟数位计算
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //读取正序数值
        StringBuilder a1Str = new StringBuilder();
        StringBuilder a2Str = new StringBuilder();
        while (l1 != null) {
            a1Str = a1Str.append(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            a2Str = a2Str.append(l2.val);
            l2 = l2.next;
        }

        long a1 = Long.parseLong(a1Str.reverse().toString());
        long a2 = Long.parseLong(a2Str.reverse().toString());
        long a3 = a1 + a2;


        char[] arrs = ("" + a3).toCharArray();
        int len = arrs.length - 1;

        ListNode head = null;
        ListNode other = null;
        for (int i = len; i >= 0; i--) {
            int a = Integer.parseInt(arrs[i] + "");
            //构建链表
            if (head == null) {
                head = new ListNode(a);
                other = head;
            } else {
                ListNode temp = new ListNode(a);
                other.next = temp;
                other = temp;
            }

        }

        return head;

    }

    /**
     * copy:模拟数位运算
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_v2(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;

        int carry = 0;

        //342 + 465 = 807

        while (p != null || q != null) {

            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
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
     * 创建链表
     *
     * @return
     */
    public ListNode create() {
        ListNode head = new ListNode(2);
        ListNode two = new ListNode(4);
        ListNode three = new ListNode(3);
        head.next = two;
        two.next = three;
        three.next = null;
        return head;
    }

    public ListNode _create() {
        ListNode head = new ListNode(5);
        ListNode two = new ListNode(6);
        ListNode three = new ListNode(4);
        head.next = two;
        two.next = three;
        three.next = null;
        return head;
    }


    public static void main(String[] args) {
        _2 app = new _2();
        app.print(app.addTwoNumbers_v2(app.create(), app._create()));

    }
}
