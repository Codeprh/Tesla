package javaOffer._1;

/**
 * 描述:
 * 字符串的比较
 *
 * @author Noah
 * @create 2020-02-23 16:20
 */
public class StringSample {

    public static void main(String[] args) {

        String s = new String("a");
        s.intern();
        String s2 = "a";

        System.out.println(s == s2);

        String aa = new String("a") + new String("a");
        aa.intern();
        String aa2 = "aa";
        System.out.println(aa == aa2);

    }
}
