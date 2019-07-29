package leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * @author Noah
 * @create 2019-07-24 14:34
 */
public class _20 {

    /**
     * todo：去网上看更好的实现
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        char[] arrs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char a : arrs) {

            if (stack.isEmpty()) {
                stack.push(a);
            } else {

                char top = stack.peek();
                if (isMatch(a, top)) {
                    stack.pop();
                } else {
                    stack.push(a);
                }
            }

        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isMatch(char a, char top) {
        if (top == '(' && a == ')') {
            return true;
        } else if (top == '{' && a == '}') {
            return true;
        } else if (top == '[' && a == ']') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        _20 app = new _20();
        System.out.println(app.isValid("{[]}"));

    }
}
