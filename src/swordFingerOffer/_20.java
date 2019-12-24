package swordFingerOffer;

/**
 * 描述:
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * <p>
 * 解题：
 * 使用正则表达式：
 * []  ： 字符集合
 * ()  ： 分组
 * ?   ： 重复 0 ~ 1 次
 * +   ： 重复 1 ~ n 次
 * *   ： 重复 0 ~ n 次
 * .   ： 任意字符
 * \\. ： 转义后的 .
 * \\d ： 数字
 *
 * @author Noah
 * @create 2019-12-24 21:57
 */
public class _20 {
    public static void main(String[] args) {
        _20 app = new _20();
        String str = new String("12e+4.3");
        System.out.println("字符串=" + str + ",是否符合数字=" + app.isNumeric(str.toCharArray()));
    }

    /**
     * 参考版：使用正则表达式来解题
     *
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
