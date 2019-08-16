package leetcode;

/**
 * 数字与运算
 * <p>
 * [5,7]
 * 0101 ->5
 * 0110 ->6
 * 0111 ->7
 *
 * @author Noah
 * @create 2019-08-13 12:38
 */
public class _201 {
    /**
     * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n = n & (n - 1);
        }
        return n;
    }

    public static void main(String[] args) {
        _201 app = new _201();
        System.out.println(app.rangeBitwiseAnd(5, 7));

    }
}
