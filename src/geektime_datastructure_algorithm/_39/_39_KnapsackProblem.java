package geektime_datastructure_algorithm._39;

/**
 * 描述:
 * 0-1背包问题，使用回溯算法解决
 *
 * @author Noah
 * @create 2019-11-25 09:06
 */
public class _39_KnapsackProblem {

    /**
     * 存储背包中物品总重量的最大值
     */
    public int maxW = Integer.MIN_VALUE;

    public static void main(String[] args) {

        _39_KnapsackProblem app = new _39_KnapsackProblem();
        int a[] = new int[]{1, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        app.f(0, 0, a, 10, 100);

        int sum = 0;
        for (int aa : a) {
            sum += aa;
        }
        System.out.println("数值的和=" + sum);
        System.out.println("最大重量=" + app.maxW);

        _39_KnapsackProblem_Simple app1 = new _39_KnapsackProblem_Simple();
        app1.f2(0, 0);
        System.out.println("最大重量=" + app1.maxW);
    }


    /**
     * cw表示当前已经装进去的物品的重量和;
     * i表示考察到哪个物品了;
     * w背包重量;
     * items表示每个物品的重量;
     * n表示物品个数
     * 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数:
     * f(0, 0, a, 10, 100)
     *
     * @param i
     * @param cw
     * @param items
     * @param n
     * @param w
     */
    public void f(int i, int cw, int[] items, int n, int w) {
        //cw==w表示装满了;i==n表示已经考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        // 已经超过可以背包承受的重量的时候，就不要再装了
        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    /**
     * 简单版本：使用回溯算法求解0-1背包问题
     */
    public static class _39_KnapsackProblem_Simple {
        // 回溯算法实现。注意:我把输入的变量都定义成了成员变量。
        private int maxW = Integer.MIN_VALUE; // 结果放到maxW中
        private int[] weight = new int[]{6, 1, 1, 1, 3}; // 物品重量
        private int n = 5; // 物品个数
        private int w = 9; // 背包承受的最大重量
        private boolean[][] mem = new boolean[5][10]; // 备忘录，默认值false

        /**
         * 常规方法：存在冗余计算
         *
         * @param i
         * @param cw
         */
        public void f(int i, int cw) { // 调用f(0, 0)
            if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了

                if (cw > maxW) maxW = cw;
                return;
            }

            f(i + 1, cw); // 选择不装第i个物品
            if (cw + weight[i] <= w) {
                f(i + 1, cw + weight[i]); // 选择装第i个物品
            }

        }

        /**
         * 优化版本：使用数组存储临时值
         *
         * @param i
         * @param cw
         */
        public void f2(int i, int cw) { // 调用f(0, 0)
            if (cw == w || i == n) { // cw==w表示装满了，i==n表示物品都考察完了
                if (cw > maxW) maxW = cw;
                return;
            }
            if (mem[i][cw]) return; // 重复状态
            mem[i][cw] = true; // 记录(i, cw)这个状态
            f2(i + 1, cw); // 选择不装第i个物品
            if (cw + weight[i] <= w) {
                f2(i + 1, cw + weight[i]); // 选择装第i个物品

            }
        }
    }
}
