package swordFingerOffer.book;

/**
 * 描述:
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 * @author Noah
 * @create 2020-09-01 8:18 上午
 */
public class p15_hammingWeight {

    public static void main(String[] args) {
        p15_hammingWeight app = new p15_hammingWeight();
        app.hammingWeight(5);
    }

    /**
     * 位运算
     *
     * @param n
     * @return
     */
    public int hammingWeight_right(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }

    /**
     * noah版本：直接利用api
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
