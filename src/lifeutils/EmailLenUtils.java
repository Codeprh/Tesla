package lifeutils;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 * 邮箱长度
 *
 * @author Noah
 * @create 2020-03-17 09:36
 */
public class EmailLenUtils {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("codingprh@gmail.com", "935509950@qq.com");
        for (String l : list) {
            System.out.println(l + ",长度=" + l.length());
        }
    }
}
