package swordFingerOffer.book;

/**
 * 描述:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * @author Noah
 * @create 2020-09-01 7:41 上午
 */
public class p10_numWays {

    public int numWays_right2(int n) {
        int a = 1, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }


    /**
     * 循环处理:取从下往上的方法，把计算过的中间项保存起来，避免重复计算导致递归调用栈溢出
     *
     * @param n
     * @return
     */
    public int numWays_right(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            //由于这题要求的是结果要对1000000007求余
            dp[i] %= 1000000007;
        }
        return dp[n];
    }

    /**
     * 爬楼梯的for循环处理
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int ret = 0;

        int pre = 2;
        int prepre = 1;

        for (int i = 3; i <= n; ++i) {
            ret = pre + prepre;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }

    /**
     * noah版本：递归实现，有重复计算的问题哦
     *
     * @param n
     * @return
     */
    public int numWays(int n) {

        if (n <= 1) {
            return 1;
        }

        if (n < 3) {
            return n;
        }

        return numWays(n - 1) + numWays(n - 2);

    }
}
