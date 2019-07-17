package leetcode;

import java.util.Stack;

/**
 * @author Noah
 * @create 2019-07-17 09:25
 */
public class _9 {
    /**
     * 第一个版本：暴力破解法，使用栈
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {

        Stack<Character> s = new Stack<>();
        int res = 0;

        char[] arrs = String.valueOf(x).toCharArray();
        for (char a : arrs) {
            s.push(a);
        }
        StringBuilder sb = new StringBuilder();
        while (!(s.empty())) {
            sb.append(s.pop());
        }
        try {
            res = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        if (res == x) {
            return true;
        }
        return false;
    }

    /**
     * 第二个版本：暴力破解，直接把字符串反转
     *
     * @param x
     * @return
     */
    public boolean isPalindrome_v2(int x) {
        String reverseStr = new StringBuilder("" + x).reverse().toString();
        return ("" + x).equals(reverseStr);
    }


    public static void main(String[] args) {
        _9 app = new _9();
        System.out.println(app.isPalindrome_v2(121));

    }
}
