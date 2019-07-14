package leetcode;

import java.util.Stack;

/**
 * @author codingprh
 * @create 2019-07-11 09:31
 */
public class _7 {
    /**
     * 233：暴力破解法
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        //[−231, 231 − 1]

        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }

        boolean isNeg = false;
        char[] arrs;

        if (x < 0) {
            isNeg = true;
            String temp = String.valueOf(x);
            arrs = temp.substring(1).toCharArray();
        } else {
            arrs = String.valueOf(x).toCharArray();
        }


        int start = 0;
        int len = arrs.length - 1;

        for (; start <= len / 2; start++) {
            char temp = arrs[start];
            arrs[start] = arrs[len - start];
            arrs[len - start] = temp;
        }

        long result = Long.parseLong(new String(arrs));
        if (isNeg) {
            result = Long.parseLong("-" + new String(arrs));
        }

        if (result <= Integer.MAX_VALUE && result >= Integer.MIN_VALUE) {
            return (int) result;
        } else {
            return 0;
        }
    }

    /**
     * 第二个版本：使用栈
     *
     * @param x
     * @return
     */
    public int reverse_v2(int x) {

        boolean isNeg = false;
        char[] arrs;

        if (x < 0) {
            isNeg = true;
            arrs = String.valueOf(x).substring(1).toCharArray();
        } else {
            arrs = String.valueOf(x).toCharArray();
        }


        Stack<Character> stack = new Stack<>();
        for (char a : arrs) {
            stack.push(a);
        }

        StringBuilder aa = new StringBuilder();
        while (!stack.empty()) {
            aa.append(stack.pop());
        }
        long result = 0;
        if (isNeg) {
            result = Long.parseLong("-" + new String(aa));
        } else {
            result = Long.parseLong(new String(aa));
        }

        if (result <= Integer.MAX_VALUE && result >= Integer.MIN_VALUE) {
            return (int) result;
        } else {
            return 0;
        }

    }

    /**
     * 运算实现栈操作并且不会越界
     *
     * @param x
     * @return
     */
    public int reverse_v3(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        _7 app = new _7();
        System.out.println(app.reverse_v3(123));
    }

}
