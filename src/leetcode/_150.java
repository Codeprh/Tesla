package leetcode;

import java.util.Stack;

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

    public int evalRPN(String[] tokens) {

        Stack<Integer> numStack = new Stack<>();
        Stack<String> charStack = new Stack<>();
        int result = 0;

        for (String toke : tokens) {

            if (Character.isDigit(toke.charAt(0))) {
                int intToken = Integer.parseInt(toke);
                numStack.push(intToken);
            } else {
                charStack.push(toke);
            }

            while (numStack.size() >= 2 && !charStack.isEmpty()) {

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


    public static void main(String[] args) {
        _150 app = new _150();
        String[] strs = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("逆波兰式计算结果=" + app.evalRPN(strs));

    }
}
