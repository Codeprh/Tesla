package leetcode;

import java.util.Stack;

/**
 * 描述:
 * 翻转字符串里的单词
 * <p>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 栗子1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 栗子2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 栗子3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * @author Noah
 * @create 2019-12-11 09:33
 */
public class _151 {

    public String reverseWords(String s) {

        char[] arrs = s.toCharArray();
        Stack<String> stack = new Stack<>();

        boolean begin = false;
        int j = 1;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arrs.length; i++) {

            char c = arrs[i];


            if (!Character.isSpaceChar(c)) {
                begin = true;
            }


            if (begin && !Character.isSpaceChar(c)) {
                sb.append(c);
            }

            if (begin) {

                if (Character.isSpaceChar(c) || i == arrs.length - 1) {

                    if (j == 1) {
                        j++;
                        stack.push(sb.toString());
                    } else {
                        stack.push(sb.append(" ").toString());
                    }
                    sb = new StringBuilder();
                    begin = false;

                }

            }

        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        _151 app = new _151();
        String str = new String("  hello world!  ");
        System.out.println(app.reverseWords(str).trim());

        String[] arrs = str.split(" ");
        for (String a : arrs) {
            System.out.println(a);
        }

    }
}
