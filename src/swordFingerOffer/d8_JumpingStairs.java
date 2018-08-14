package swordFingerOffer;

/**
 * 跳台阶：斐波那契数列算法一致
 * <p>
 * 题目描述：一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 */
public class d8_JumpingStairs {
    /**
     * 不好的算法实现，使用O(N)+O(N)
     * <p>
     * 可以参考，斐波那契数列，使用一个临时变量：O(N)+1
     *
     * @param n
     * @return
     */
    public int JumpFloor(int n) {
        if (n == 1)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n - 1];
    }

    public static void main(String[] args) {
        d8_JumpingStairs app = new d8_JumpingStairs();
        System.out.println(app.JumpFloor(6));
    }
}
