package swordFingerOffer.book;

/**
 * 描述:
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * @author Noah
 * @create 2020-08-30 10:46 上午
 */
public class p55_isBalancedTree {

    public static void main(String[] args) {

    }

    /**
     * noah版本：不会
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return false;
    }

    /**
     * 使用后序递归遍历，判断左右子树，高度差Math.abs<2，然后树高度
     *
     * @param root
     * @return
     */
    public boolean isBalanced_right(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if (left == -1) return -1;
        int right = recur(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
