package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

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
        TreeNode head = app.invertTree_v2(treeNode);
        System.out.println(app.LevelTraversal(head));
    }

    /**
     * 思想1：但是在leetcode当中，可能测试不通过
     * 满二叉树是完全二叉树的一种特殊情况。
     * 数组存储一棵完全二叉树
     * <p>
     * 思路二：下面的代码实现，采用递归的方案实现：leetcode._104#maxDepth(leetcode._104.BinaryTreeNode)求解树的最大深度
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        //采用递归形势
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);


        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * 递归的都能使用迭代器实现
     * bfs（宽度优先）
     *
     * @param root
     * @return
     */
    public TreeNode invertTree_v2(TreeNode root) {

        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }


        }

        return root;
    }

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> LevelTraversal(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add((int) node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
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
