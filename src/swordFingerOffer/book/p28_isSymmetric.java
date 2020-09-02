package swordFingerOffer.book;

/**
 * 描述:
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * @author Noah
 * @create 2020-09-02 7:53 上午
 */
public class p28_isSymmetric {

    public static void main(String[] args) {

    }

    /**
     * 递归版本
     *
     * @param root
     * @return
     */
    public boolean isSymmetric_right(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }

    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) return true;
        if (L == null || R == null || L.val != R.val) return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }

    /**
     * noah版本：广度优先算法
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return true;
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
