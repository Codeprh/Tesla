package swordFingerOffer.book;

/**
 * 描述:输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * @author Noah
 * @create 2020-09-02 8:06 上午
 */
public class p29_spiralOrder {

    public static void main(String[] args) {

    }

    /**
     * 控制好临界值，直接遍历矩阵
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder_right(int[][] matrix) {

        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;

    }

    /**
     * noah版本：思路清晰
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {

        return new int[0];
    }
}
