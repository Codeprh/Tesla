package swordFingerOffer.book;

/**
 * 描述:给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * @author Noah
 * @create 2020-08-31 8:24 上午
 */
public class p68_lowestCommonAncestor {

    /**
     * 利用二叉搜索树的特性：循环处理
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_right(TreeNode root, TreeNode p, TreeNode q) {

        while (root != null) {
            if (root.val < p.val && root.val < q.val) // p,q 都在 root 的右子树中
                root = root.right; // 遍历至右子节点
            else if (root.val > p.val && root.val > q.val) // p,q 都在 root 的左子树中
                root = root.left; // 遍历至左子节点
            else break;
        }
        return root;

    }

    /**
     * 递归处理
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_right2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor_right2(root.right, p, q);
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor_right2(root.left, p, q);
        return root;
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
