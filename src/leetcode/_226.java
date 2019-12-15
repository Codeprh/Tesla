package leetcode;

/**
 * 描述:
 * 翻转二叉树
 * <p>
 * 输入：
 *   4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 *   4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 *
 * @author Noah
 * @create 2019-12-14 21:00
 */
public class _226 {
    /**
     * todo:实现自己的第一版本功能
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        return null;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
