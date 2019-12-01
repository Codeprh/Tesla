package leetcode;

/**
 * 描述:
 * 递归求解爬楼梯问题：
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 *
 * @author Noah
 * @create 2019-12-01 21:29
 */
public class _70 {
    /**
     * 递归版本
     * todo：优化
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        _70 app = new _70();
        System.out.println("总共爬楼梯的方法=" + app.climbStairs(3));
    }
}
