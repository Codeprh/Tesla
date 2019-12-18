package leetcode;

/**
 * 描述:
 * 翻转二叉树
 * <p>
 * 输入：
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 *
 * @author Noah
 * @create 2019-12-14 21:00
 */
public class _226 {

    public static void main(String[] args) {
        _226 app = new _226();
        TreeNode treeNode = app.fullBinarySearchTrees();
        app.invertTree(treeNode);

    }

    /**
     * 满二叉树是完全二叉树的一种特殊情况。
     * 数组存储一棵完全二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        //如果是采用数组的形势存储完全满二叉树，耗时。

        //采用递归形势
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        //left.val;
        return null;
    }

    /**
     * 创建一个满二叉查找树
     *
     * @return
     */
    public TreeNode fullBinarySearchTrees() {

        TreeNode _4 = new TreeNode(4);
        TreeNode _2 = new TreeNode(2);
        TreeNode _7 = new TreeNode(7);
        TreeNode _1 = new TreeNode(1);
        TreeNode _3 = new TreeNode(3);
        TreeNode _6 = new TreeNode(6);
        TreeNode _9 = new TreeNode(9);

        _4.left = _2;
        _4.right = _7;

        _2.left = _1;
        _2.right = _3;

        _7.left = _6;
        _7.right = _9;
        return _4;

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
