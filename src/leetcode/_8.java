package leetcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * todo：
 *
 * @author Noah
 * @create 2019-08-07 16:26
 */
public class _8 {
    private Pattern pattern = Pattern.compile("^[-+]?[0-9]+(\\.[0-9]+)?$");

    public int myAtoi(String str) {
        if (str == null || str.isEmpty() || str.length() == 0) {
            return 0;
        }
        Matcher matcher = pattern.matcher(str);
        String r = matcher.replaceAll("");

        int max = Integer.MAX_VALUE;
        int mix = Integer.MIN_VALUE;

        System.out.println("r=" + r);
        return 0;
    }

    public static void main(String[] args) {
        _8 app = new _8();
        app.myAtoi("-42");

    }
}
