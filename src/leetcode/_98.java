package leetcode;

/**
 * 描述:
 * 验证二叉查找树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * @author Noah
 * @create 2019-12-15 21:33
 */
public class _98 {
    /**
     * todo：代码实现
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return false;
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

