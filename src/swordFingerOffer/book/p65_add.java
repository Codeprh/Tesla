package swordFingerOffer.book;

/**
 * 描述:写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * @author Noah
 * @create 2020-08-31 7:59 上午
 */
public class p65_add {

    public static void main(String[] args) {

        p65_add app = new p65_add();

        System.out.println(app.add_right(4, 1));

    }

    /**
     * 位运算来替换加法
     *
     * @param a
     * @param b
     * @return
     */
    public int add_right(int a, int b) {

        while (b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }
}
