package geektime_datastructure_algorithm._39;

/**
 * 描述:
 * 参考版
 * 使用回溯思想解决八皇后问题
 *
 * @author Noah
 * @create 2019-11-25 08:48
 */
public class _39_EightQueensProblem {
    /**
     * 全局或成员变量,下标表示行,值表示queen存储在哪一列
     */
    int[] result = new int[8];

    public static void main(String[] args) {
        _39_EightQueensProblem app = new _39_EightQueensProblem();
        app.cal8queens(0);
    }

    /**
     * // 调用方式:cal8queens(0)
     * 八皇后问题
     *
     * @param row
     */
    public void cal8queens(int row) {
        if (row == 8) {
            printQueens(result);// 8个棋子都放置好了，打印结果
            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有8中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第row行的棋子放到了column列
                cal8queens(row + 1); // 考察下一行
            }
        }
    }

    /**
     * 判断row行column列放置是否合适
     *
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {
        int leftup = column - 1, rightup = column + 1;
        for (int i = row - 1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第i行的column列有棋子吗?
            if (leftup >= 0) { // 考察左上对⻆线:第i行leftup列有棋子吗?
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对⻆线:第i行rightup列有棋子吗?
                if (result[i] == rightup) return false;
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    /**
     * 打印出一个二维矩阵
     *
     * @param result
     */
    private void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
