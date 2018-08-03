package swordFingerOffer;

/**
 * 二维数组中的查找
 * <p>
 * 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * <p>
 * 解题思路：从右上角开始查找。矩阵中的一个数，它左边的数都比它小，下边的数都比它大。因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间。
 */
public class d2_Two_dimensionalArrayLookup {
    public boolean twoDimensionalArrayLookup(int[][] twoArray, int target) {
        if (twoArray == null || twoArray.length <= 0) {
            return false;
        }
        int len = twoArray.length;
        int rows = len, cols = twoArray[0].length;
        int r = 0, c = cols - 1;
        while (r <= rows-1 && c >= 0) {
            if (twoArray[r][c] == target) {
                return true;
            } else if (twoArray[r][c] < target) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] twoArray = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 31}
        };
        d2_Two_dimensionalArrayLookup app = new d2_Two_dimensionalArrayLookup();
        System.out.println(app.twoDimensionalArrayLookup(twoArray, 102));
        //System.out.println(Arrays.deepToString(twoArray));


    }

    public boolean Find(int target, int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1; // 从右上角开始
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c])
                return true;
            else if (target > matrix[r][c])
                r++;
            else
                c--;
        }
        return false;
    }
}
