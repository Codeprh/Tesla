package swordFingerOffer;

import java.io.InputStream;
import java.util.Stack;

/**
 * 大题目：用两个栈实现队列
 * <p>
 * 题目描述：用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 * <p>
 * 解题思路：in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，需要先进入 out 栈，此时元素出栈顺序再一次被反转，因此出栈顺序就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。
 */
public class d6_QueueWithTwoStacks {
    private Stack<Integer> in = new Stack<>();
    private Stack<Integer> out = new Stack<>();

    /**
     * 入队列
     *
     * @param val
     */
    public void intoTheQueue(Integer val) {
        in.push(val);
    }

    /**
     * 出队列
     */
    public Integer outQueue() {
        while (!in.empty()) {
            out.push(in.pop());
        }
        Integer result = out.pop();
        while (!out.empty()) {
            in.push(out.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        d6_QueueWithTwoStacks app = new d6_QueueWithTwoStacks();
        Integer[] arry = new Integer[]{1, 2, 3};
        for (Integer item : arry) {
            app.intoTheQueue(item);
        }
        System.out.println(app.outQueue() + "=====");

    }
}
