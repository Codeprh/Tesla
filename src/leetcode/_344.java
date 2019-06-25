package leetcode;

import java.util.Arrays;

/**
 * @author codingprh
 * @create 2019-06-25 15:45
 */
public class _344 {
    public void reverseString(char[] s) {
        int length = s.length - 1;

        for (int i = length; i > length / 2; i--) {
            char temp = s[i];
            s[i] = s[length - i];
            s[length - i] = temp;
        }

        System.out.println(Arrays.toString(s));
    }

    public static void main(String[] args) {
        _344 start = new _344();
        char[] s = {'1', '1',  '3', '3'};
        start.reverseString(s);
    }
}
