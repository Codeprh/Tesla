package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * //[
 * //     [2],
 * //    [3,4],
 * //   [6,5,7],
 * //  [4,1,8,3]
 * //]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * @author Noah
 * @create 2019-12-23 21:25
 */
public class _120 {

    int row;

    public int minimumTotal(List<List<Integer>> triangle) {
        row = triangle.size();
        return helper(0, 0, triangle);
    }

    /**
     * 递归处理
     *
     * @param level
     * @param c
     * @param triangle
     * @return
     */
    private int helper(int level, int c, List<List<Integer>> triangle) {
        System.out.println("helper: level=" + level + " c=" + c);
        if (level == row - 1) {
            return triangle.get(level).get(c);
        }
        int left = helper(level + 1, c, triangle);
        int right = helper(level + 1, c + 1, triangle);
        return Math.min(left, right) + triangle.get(level).get(c);
    }

    /**
     * 自底向上, DP 【AC】
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_v2(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] minlen = new int[row + 1];
        for (int level = row - 1; level >= 0; level--) {
            for (int i = 0; i <= level; i++) {   //第i行有i+1个数字
                minlen[i] = Math.min(minlen[i], minlen[i + 1]) + triangle.get(level).get(i);
            }
        }
        return minlen[0];
    }


    public static void main(String[] args) {

        _120 app = new _120();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 0));
        triangle.add(Arrays.asList(4, 1, 8, 0));

        System.out.println("三角形最小路径=" + app.minimumTotal_v2(triangle));

    }

}
