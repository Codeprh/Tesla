package geektime_datastructure_algorithm._41;

/**
 * 描述:
 * 使用：回溯算法or动态规划,来解决矩阵最短路径问题
 *
 * @author Noah
 * @create 2019-11-27 09:11
 */
public class _41_Matrix_ShortestPath {
    /**
     * 回溯算法：最短路径
     */
    private int minDist = Integer.MAX_VALUE;

    /**
     * 使用回溯算法思想：穷举（搜索）所有可能的路径
     * 调用方式:minDistBacktracing(0, 0, 0, w, n);
     *
     * @param i
     * @param j
     * @param dist
     * @param w
     * @param n
     */
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达了n-1, n-1这个位置了,这里看着有点奇怪哈,你自己举个例子看下
        if (i == n && j == n) {
            if (dist < minDist) minDist = dist;
            return;
        }
        if (i < n) { // 往下走,更新i=i+1, j=j
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) { // 往右走,更新i=i, j=j+1
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }

    /**
     * 使用动态规划思想：解决矩阵最短路径问题（状态转义表法）
     *
     * @param matrix
     * @param n
     * @return
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }


    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private int n = 4;
    private int[][] mem = new int[4][4];

    /**
     * 动态规划求解：矩阵最短路径（状态转义方程法）
     * <p>
     * 方程：min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     * <p>
     * 调用minDist(n-1, n-1);
     *
     * @param i
     * @param j
     * @return
     */
    public int minDist(int i, int j) {
        if (i == 0 && j == 0) return matrix[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }
        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    public static void main(String[] args) {

        _41_Matrix_ShortestPath app = new _41_Matrix_ShortestPath();
        //1->2->1->2->6->4->3
        System.out.println("矩阵最短路径=" + app.minDist(app.n - 1, app.n - 1));
    }


}
