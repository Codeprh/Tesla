package swordFingerOffer.book;

import java.util.Stack;

/**
 * 描述:定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * @author Noah
 * @create 2020-09-02 8:13 上午
 */
public class p30_MinStack {

    /**
     * 正确结果：使用双栈来实现min函数
     */
    static class MinStack {


        Stack<Integer> A, B;

        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
        }

        public void push(int x) {
            A.add(x);
            if (B.empty() || B.peek() >= x)
                B.add(x);
        }

        public void pop() {
            if (A.pop().equals(B.peek()))
                B.pop();
        }

        public int top() {
            return A.peek();
        }

        public int min() {
            return B.peek();

        }
    }
}