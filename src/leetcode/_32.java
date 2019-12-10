package leetcode;

import java.util.Objects;

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

    public static void main(String[] args) {
        _32 app = new _32();
        //6
        System.out.println("最长括号匹配=" + app.longestValidParentheses("()(())"));
    }
}
