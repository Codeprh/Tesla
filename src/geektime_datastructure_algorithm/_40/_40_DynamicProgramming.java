package geektime_datastructure_algorithm._40;

/**
 * 描述:
 * 使用动态规划来求解0-1背包问题
 *
 * @author Noah
 * @create 2019-11-26 09:18
 */
public class _40_DynamicProgramming {

    public static void main(String[] args) {
        _40_DynamicProgramming app = new _40_DynamicProgramming();
        System.out.println("最大重量=" + app.knapsack2(new int[]{2, 2, 4, 6, 3}, 5, 9));
    }

    /**
     * 0-1背包问题：动态规划常规求解
     * weight:物品重量，n:物品个数，w:背包可承载重量
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值false
        states[0][0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; ++i) {// 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {// 输出结果
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }

    /**
     * 0-1背包问题：优化动态规划求解，降低空间复杂度
     *
     * @param items
     * @param n
     * @param w
     * @return
     */
    public int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1]; // 默认值false
        states[0] = true; // 第一行的数据要特殊处理，可以利用哨兵优化
        states[items[0]] = true;
        for (int i = 1; i < n; ++i) {// 动态规划
            for (int j = w - items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {// 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }
}
