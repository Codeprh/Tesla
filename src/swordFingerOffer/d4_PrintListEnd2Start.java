package swordFingerOffer;

import java.util.Stack;

/**
 * 从尾到头打印链表
 * <p>
 * 题目描述：输入链表的第一个节点，从尾到头反过来打印出每个结点的值。
 */
public class d4_PrintListEnd2Start {
    /**
     * 使用栈
     *
     * @param list
     */
    public static void printListFromEndToHead(MyList list) {
        Stack<Object> stack = new Stack<>();
        MyList.Node node = null;
        while ((node = list.getNodeNext(node)) != null) {
            stack.push(node);
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 使用递归的方式
     * todo
     */
    public MyList.Node printListFromEndToHeadByRecursive(MyList.Node node, MyList list) {
        return null;
    }


    public static void main(String[] args) {
        MyList list = new MyList();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        d4_PrintListEnd2Start.printListFromEndToHead(list);
    }


}
