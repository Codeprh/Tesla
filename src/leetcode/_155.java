package leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author codingprh
 * @create 2019-07-10 17:33
 */
public class _155 {
    static class MinStack {
        public LinkedList<Integer> stack = new LinkedList<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            stack.addLast(x);
        }

        public void pop() {
            stack.removeLast();
        }

        public int top() {
            return stack.getLast();
        }

        public int getMin() {
            return stack.stream().min(Comparator.comparing(Integer::valueOf)).get();
        }
    }

    static class MinStackV2 {
        public Stack<Integer> data = new Stack<>();
        public Stack<Integer> helper = new Stack<>();

        /**
         * initialize your data structure here.
         */
        public MinStackV2() {

        }

        public void push(int x) {
            data.push(x);
            if (helper.empty() || helper.peek() > x) {
                helper.push(x);
            } else {
                helper.push(helper.peek());
            }
        }

        public void pop() {
            data.pop();
            helper.pop();

        }

        public int top() {
            return data.peek();

        }

        public int getMin() {
            return helper.peek();
        }
    }

    public static void main(String[] args) {
        _155.MinStackV2 app = new _155.MinStackV2();
        app.push(1);

        app.push(0);
        app.push(2);
        app.push(1);
        app.push(1);
        app.push(1);
        System.out.println("获取到最小值=" + app.getMin());
        System.out.println("获取到栈顶元素=" + app.top());
        app.pop();
        System.out.println("删除元素之后的栈=" + app.data);
        System.out.println("删除元素之后的栈=" + app.helper);


    }
}
