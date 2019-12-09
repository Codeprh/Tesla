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
     * 第一版本：自己实现，无法求解。
     * 能够使用"猜"的思路去解决问题，但是没有想到二分查找去实现。
     *
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

    /**
     * 参考版本：二分查找思想解决
     *
     * @param x
     * @return
     */
    public int mySqrt_v2(int x) {
        // 注意：针对特殊测试用例，例如 2147395599
        // 要把搜索的范围设置成长整型
        // 为了照顾到 0 把左边界设置为 0
        long left = 0;
        // # 为了照顾到 1 把右边界设置为 x // 2 + 1
        long right = x / 2 + 1;
        while (left < right) {
            // 注意：这里一定取右中位数，如果取左中位数，代码会进入死循环
            // long mid = left + (right - left + 1) / 2;
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        // 因为一定存在，因此无需后处理
        return (int) left;
    }

    /**
     * 自己实现：采用二分查找思路来求解平方根
     *
     * @param x
     * @return
     */
    public int mySqrt_v3(int x) {
        if (x == 0) {
            return 0;
        }

        int left = 1;
        int right = x / 2 + 1;

        while (left < right) {
            //todo:临界值是否稳妥
            int mid = left + (right - left + 1) / 2;
            int rmid = mid * mid;

            if (x < rmid) {
                right = mid - 1;
            } else {
                left = mid;
            }

        }

        return left;
    }

    /**
     * 参考版本
     * 牛顿法求解平方根
     *
     * @param x
     * @return
     */
    public int mySqrt_v4(int x) {
        long a = x;
        while (a * a > x) {
            a = (a + x / a) / 2;
        }
        return (int) a;
    }

    /**
     * 使用sdk求解
     *
     * @return
     */
    public int mySqrt_v5(int x) {
        return (int) Math.sqrt(x);
    }

    public static void main(String[] args) {
        _69 app = new _69();
        System.out.println("x的平方根_v4=" + app.mySqrt_v4(9132));
        System.out.println("x的平方根_v3=" + app.mySqrt_v3(9132));
    }
}
