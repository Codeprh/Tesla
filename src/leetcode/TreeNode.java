package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 描述:
 * TreeNode，leetcode题目的int型的树
 *
 * @author Noah
 * @create 2019-12-22 09:55
 */
public class TreeNode<T> {

    public T val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(T val) {
        this.val = val;
    }

    /**
     * 参考：{@link geektimeDatastructureAlgorithm._23.TreeNode}
     * <p>
     * 创建int的二叉查找树，链表存储&&非完全二叉树
     * //     10
     * //   /   \
     * //   9    14
     * //       /   \
     * //       13  16
     * //       /
     * //       11
     *
     * @return
     */
    public static <T> TreeNode creatBinarySearchTreesByLinked(boolean isInt) {
        TreeNode _10 = new TreeNode(10);
        TreeNode _9 = new TreeNode(9);
        TreeNode _14 = new TreeNode(14);

        TreeNode _13 = new TreeNode(13);
        TreeNode _16 = new TreeNode(16);
        TreeNode _11 = new TreeNode(11);

        _10.left = _9;
        _10.right = _14;

        _14.left = _13;
        _14.right = _16;

        _13.left = _11;

        return _10;
    }

    /**
     * todo:创建二叉查找树，数组存储&&完全二叉树
     *
     * @param <T>
     * @return
     */
    public static <T> TreeNode creatBinarySearchTreesByArray(boolean isInt) {
        return null;
    }

    /**
     * todo:创建满二叉树，链表存储
     *
     * @param isInt
     * @param <T>
     * @return
     */
    public static <T> TreeNode createFullBinaryTreesByLinked(boolean isInt) {
        return null;

    }

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> LevelTraversal(TreeNode root) {
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


}
