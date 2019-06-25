package leetcode;


/**
 * @author codingprh
 * @create 2019-06-25 16:22
 */
public class _557 {
    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        String sb = new String();
        for (int i = 0; i < arr.length; i++) {
            sb = sb + reverse(arr[i]) + " ";
        }
        System.out.println(sb);
        return sb.toString();
    }

    public String reverse(String str) {
        char[] s = str.toCharArray();
        int length = s.length - 1;
        for (int i = length; i > length / 2; i--) {
            char temp = s[i];
            s[i] = s[length - i];
            s[length - i] = temp;
        }

        return new java.lang.String(s);
    }

    public static void main(String[] args) {
        _557 start = new _557();
        start.reverseWords("Let's take LeetCode contest");
    }
}
