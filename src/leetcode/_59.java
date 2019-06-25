package leetcode;

import java.util.Arrays;

/**
 * @author codingprh
 * @create 2019-06-22 18:10
 */
public class _59 {
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        // 遍历填充的数字
        int num = 1;
        // 每一圈左边起始位置和右边结束位置 由于是正方形所以列相同
        int left = 0, right = n - 1;
        while (left <= right) {
            // 最中间的一个
            if (left == right) {
                matrix[left][right] = num++;
            }
            // 最上面一行 除去最后一个
            for (int i = left; i < right; i++) {
                matrix[left][i] = num++;
            }
            // 最右边一列 除去最后一个
            for (int i = left; i < right; i++) {
                matrix[i][right] = num++;
            }
            // 最下面一行 除去最后一个（逆序）
            for (int i = right; i > left; i--) {
                matrix[right][i] = num++;
            }
            // 最左边一列 除去最后一个（逆序）
            for (int i = right; i > left; i--) {
                matrix[i][left] = num++;
            }
            // 一圈遍历结束 遍历下一圈
            left++;
            right--;
        }
        return matrix;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(my_generateMatrix_v3(10)));

    }

    public static int[][] generateMatrix_v2(int n) {
        int[][] arr = new int[n][n];
        int c = 1, j = 0;
        while (c <= n * n) {

            for (int i = j; i < n - j; i++)
                arr[j][i] = c++;
            for (int i = j + 1; i < n - j; i++)
                arr[i][n - j - 1] = c++;
            for (int i = n - j - 2; i >= j; i--)
                arr[n - j - 1][i] = c++;
            for (int i = n - j - 2; i > j; i--)
                arr[i][j] = c++;

            j++;
        }

        return arr;
    }

    public static int[][] my_generateMatrix_v3(int n) {
        int[][] result = new int[n][n];
        int left = 0, right = n - 1;
        int num = 1;
        while (left <= right) {
            if (left == right) {
                result[left][right] = num++;
            }
            //处理第一行,没有处理最后一个
            for (int i = left; i < right; i++) {
                result[left][i] = num++;
            }
            //处理最后一列,没有处理最后一个
            for (int i = left; i < right; i++) {
                result[i][right] = num++;
            }
            //处理最后一列,没有处理最后一个
            for (int i = right; i > left; i--) {
                result[right][i] = num++;
            }
            for (int i = right; i > left; i--) {
                result[i][left] = num++;
            }
            left++;
            right--;

        }
        return result;
    }


}
