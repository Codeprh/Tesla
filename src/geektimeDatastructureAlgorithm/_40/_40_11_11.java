package geektimeDatastructureAlgorithm._40;

/**
 * 描述:
 * 使用动态规划来解决双11凑单问题
 *
 * @author Noah
 * @create 2019-11-27 08:12
 */
public class _40_11_11 {

    public static void main(String[] args) {

        _40_11_11 app = new _40_11_11();
        //int[] items = new int[]{150, 100, 300, 400, 800, 50, 10, 30, 20};
        int[] items = new int[]{150, 100, 300, 400, 800};
        app.double11advance(items, items.length, 200);

    }

    /**
     * items商品价格，n商品个数, w表示满减条件，比如200
     *
     * @param items
     * @param n
     * @param w
     */
    public void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3 * w + 1];//超过3倍就没有薅羊毛的价值了
        states[0][0] = true; // 第一行的数据要特殊处理
        states[0][items[0]] = true;
        for (int i = 1; i < n; ++i) {// 动态规划
            for (int j = 0; j <= 3 * w; ++j) {// 不购买第i个商品
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= 3 * w - items[i]; ++j) {//购买第i个商品
                if (states[i - 1][j] == true) states[i][j + items[i]] = true;
            }
        }
        int j;
        for (j = w; j < 3 * w + 1; ++j) {
            if (states[n - 1][j] == true) break; // 输出结果大于等于w的最小值
        }
        if (j == -1) return; // 没有可行解
        for (int i = n - 1; i >= 1; --i) { // i表示二维数组中的行，j表示列
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                System.out.print(items[i] + " "); // 购买这个商品
                j = j - items[i];
            }  //else 没有购买这个商品，j不变。
        }
        if (j != 0) System.out.print(items[0]);
    }
}
