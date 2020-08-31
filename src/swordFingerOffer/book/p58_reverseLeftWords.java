package swordFingerOffer.book;

/**
 * 描述:
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * @author Noah
 * @create 2020-08-30 4:46 下午
 */
public class p58_reverseLeftWords {

    public static void main(String[] args) {
        String str = "abcdefg";
        p58_reverseLeftWords app = new p58_reverseLeftWords();
        System.out.println(app.reverseLeftWords(str, 2));

    }

    /**
     * noah ：api版本
     * 1、数组copy的api
     * 2、直接在str上面操作
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {

        if (s == null || s.trim().length() <= 0) {
            return "";
        }
        //需要追加到尾部
        String substring = s.substring(0, n);

        return s.substring(n) + substring;
    }
}
