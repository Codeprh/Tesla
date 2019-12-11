package leetcode;

import java.util.Objects;
import java.util.Stack;

/**
 * 描述:
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 栗子1：
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * <p>
 * 栗子2
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @author Noah
 * @create 2019-12-10 09:37
 */
public class _32 {

    /**
     * 第一版：理解错误提议，无法满足需求。
     * 题目意思理解为连续的()括号匹配
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        int r = 0;
        char[] arrs = s.toCharArray();
        int rmax = 0;

        for (int i = 0; i < arrs.length; i++) {

            if (Objects.equals(arrs[i], '(') && r == 0) {
                r++;
                continue;
            }
            //进入开始计数逻辑
            if (r > 0) {

                if (Objects.equals(arrs[i - 1], '(')) {

                    if (Objects.equals(arrs[i], ')')) {
                        r++;
                        rmax = r;
                    } else {
                        r = 1;
                    }

                } else {

                    if (Objects.equals(arrs[i], '(')) {
                        r++;
                        rmax = r;
                    } else {
                        r = 0;
                    }

                }

            }
        }
        return rmax;
    }

    /**
     * 第二版：不能实现，计算和匹配类似的都能使用双栈来解决问题.使用栈，无法解决。难受
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_v2(String s) {

        Stack<Character> leftCharStack = new Stack<>();
        Stack<Character> rightCharStack = new Stack<>();

        int r = 0;
        char[] arrs = s.toCharArray();
        int rmax = 0;

        boolean left = true;

        for (int i = 0; i < arrs.length; i++) {

            if (arrs[i] == ')') {
                left = false;
            } else {
                left = true;
            }

            if (left && (leftCharStack.isEmpty() || rightCharStack.isEmpty())) {
                leftCharStack.push(arrs[i]);
                continue;
            } else if (!left && (rightCharStack.isEmpty() || leftCharStack.isEmpty())) {
                rightCharStack.push(arrs[i]);
                if (leftCharStack.isEmpty()) {
                    continue;
                }
            }


            if (match(arrs[i], left == true ? rightCharStack.peek() : leftCharStack.peek())) {
                r += 2;
                if (left) {
                    rightCharStack.pop();
                    leftCharStack.pop();
                } else {
                    rightCharStack.pop();
                    leftCharStack.pop();
                }
            } else {

                if (left) {
                    leftCharStack.push(arrs[i]);
                } else {
                    rightCharStack.push(arrs[i]);
                }
            }

        }
        return r;
    }

    /**
     * 参考版：暴力
     * todo:动态规划思想解决
     *
     * @param s
     * @return
     */
    public int longestValidParentheses_v3(String s) {


        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;


    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    /**
     * 是否匹配
     *
     * @param c
     * @param top
     * @return
     */
    public boolean match(char c, char top) {
        if (c == '(') {
            if (top == ')') {
                return true;
            }
        } else if (c == ')') {
            if (top == '(') {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        _32 app = new _32();
        //2     (()
        System.out.println("最长括号匹配=" + app.longestValidParentheses_v3("(()"));
        //4     )()())
        System.out.println("最长括号匹配=" + app.longestValidParentheses_v3(")()())"));
        //6
        System.out.println("最长括号匹配=" + app.longestValidParentheses_v3("()(())"));
        //4
        System.out.println("最长括号匹配=" + app.longestValidParentheses_v3(")()())"));
        //4
        System.out.println("最长括号匹配=" + app.longestValidParentheses_v3("(()()"));
        //2
        System.out.println("最长括号匹配=" + app.longestValidParentheses_v3("()(()"));
    }
}
