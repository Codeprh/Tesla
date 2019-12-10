package leetcode;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述:
 * 逆波兰式求值
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 * @author Noah
 * @create 2019-12-04 16:07
 */
public class _150 {
    /**
     * todo：check代码
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {

        Stack<Integer> numStack = new Stack<>();
        Stack<String> charStack = new Stack<>();
        int result = 0;

        for (String toke : tokens) {

            Pattern pattern = Pattern.compile("^-?[0-9]+");
            Matcher isNum = pattern.matcher(toke);
            //Character.isDigit(toke.charAt(0))，无法判断负数的情况
            if (isNum.matches()) {
                int intToken = Integer.parseInt(toke);
                numStack.push(intToken);
                result = intToken;
            } else {

                charStack.push(toke);
                //字符计算逻辑
                if (numStack.size() < 2) {
                    continue;
                }
                int num1 = numStack.pop();
                int num2 = numStack.pop();

                String str = charStack.pop();
                int r = 0;

                if (str.equals("+")) {
                    r = num1 + num2;
                } else if (str.equals("-")) {
                    r = num2 - num1;
                } else if (str.equals("*")) {
                    r = num2 * num1;
                } else if (str.equals("/")) {
                    r = num2 / num1;
                }

                numStack.push(r);
                result = r;
            }

        }
        return result;
    }

    /**
     * 参考版代码
     *
     * @param tokens
     * @return
     */
    public int evalRPN_v2(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int num1 = stack.pop();
                stack.push(stack.pop() / num1);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }


    public static void main(String[] args) {
        _150 app = new _150();
        String[] strs = new String[]{"3", "-4", "+"};
        System.out.println("逆波兰式计算结果_v2=" + app.evalRPN_v2(strs));
        System.out.println("逆波兰式计算结果_v1=" + app.evalRPN(strs));

    }
}
