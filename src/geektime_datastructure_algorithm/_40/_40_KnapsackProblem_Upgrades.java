package geektime_datastructure_algorithm._40;

/**
 * 0-1背包问题升级版：
 * 我们现在引入物品价值这一变量。对于一组不同重量、不同价值、不可 分割的物品,我们选择将某些物品装入背包,在满足背包最大重量限制的前提下,背包中可装入物品的总价值最大是多少呢?
 */
public class _40_KnapsackProblem_Upgrades {

    private int maxV = Integer.MIN_VALUE; // 结果放到maxV中
    private int[] weight = {2, 2, 4, 6, 3}; // 物品的重量
    private int[] value = {3, 4, 8, 9, 6}; // 物品的价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public static void main(String[] args) {
        _40_KnapsackProblem_Upgrades app = new _40_KnapsackProblem_Upgrades();

        //2 4 3
        app.f(0, 0, 0);
        System.out.println("在满足放入最大物品下的最大价值=" + app.maxV);
    }

    /**
     * 使用回溯算法求解0-1背包升级问题
     *
     * @param i
     * @param cw
     * @param cv
     */
    public void f(int i, int cw, int cv) { // 调用f(0, 0, 0)
        if (cw == w || i == n) { // cw==w表示装满了,i==n表示物品都考察完了
            if (cv > maxV) maxV = cv;
            return;
        }
        f(i + 1, cw, cv); // 选择不装第i个物品
        if (cw + weight[i] <= w) {

            f(i + 1, cw + weight[i], cv + value[i]); // 选择装第i个物品
        }
    }

    /**
     * 动态规划求解0-1背包升级问题
     *
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) {// 初始化states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) {// 不选择第i个物品
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) { // 选择第i个物品
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n - 1][j] > maxvalue) maxvalue = states[n - 1][j];
        }
        return maxvalue;
    }
}
