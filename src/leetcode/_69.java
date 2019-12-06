package leetcode;

/**
 * 描述:
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * @author Noah
 * @create 2019-12-06 14:14
 */
public class _69 {
    /**
     * todo：check代码
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int mid = x / 2;
        int _mid = mid * mid;

        if (_mid <= x) {
            return mid;
        }

        while (_mid > x) {
            --mid;
            _mid = mid * mid;

        }
        return mid;
    }

    public static void main(String[] args) {
        _69 app = new _69();
        System.out.println("x的平方根=" + app.mySqrt(1));
    }
}
