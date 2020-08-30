package swordFingerOffer.book;

/**
 * 描述:
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * @author Noah
 * @create 2020-08-30 10:28 上午
 */
public class p55_maxDepth {
    public static void main(String[] args) {

    }

    /**
     * noah版本：求二叉树的深度，后续递归遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftLen = maxDepth(root.left);
        int rightLen = maxDepth(root.right);

        return Math.max(leftLen, rightLen) + 1;
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
