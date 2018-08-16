package swordFingerOffer;

import java.util.Arrays;

/**
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
 */
public class d9_metamorphosisJumpingStairs {
    /**
     * 这是计算前n项的和实现
     *
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }

    /**
     * 计算前一项的(n-1)*2
     *
     * @param target
     * @return
     */
    public Integer jumpFool2(Integer target) {
        if (target <= 0) {
            return 0;
        }
        Integer[] result = new Integer[target + 1];
        Arrays.fill(result, 1);
        result[0] = 0;
        for (Integer i = 2; i <= target; i++) {
            result[i] = result[i - 1] * 2;
        }
        return result[target];

    }

    public static void main(String[] args) {
        d9_metamorphosisJumpingStairs app = new d9_metamorphosisJumpingStairs();
        System.out.println(app.JumpFloorII(11));
        System.out.println(app.jumpFool2(11));

    }
}
