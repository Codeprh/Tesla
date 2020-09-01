package swordFingerOffer.book;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-09-01 7:07 上午
 */
public class p5_replaceSpace {

    public static void main(String[] args) {

    }

    /**
     * 使用数组来细粒度控制
     *
     * @param s
     * @return
     */
    public String replaceSpace_right2(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            } else {
                array[size++] = c;
            }
        }
        String newStr = new String(array, 0, size);
        return newStr;

    }

    /**
     * 直接使用stringBuilder
     *
     * @param s
     * @return
     */
    public String replaceSpace_right(String s) {
        StringBuilder res = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c == ' ') res.append("%20");
            else res.append(c);
        }
        return res.toString();

    }

    /**
     * noah：直接使用api
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
