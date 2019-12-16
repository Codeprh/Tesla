package leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最大深度:
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 返回它的最大深度 3
 *
 * @author codingprh
 * @create 2019-06-25 10:56
 */
public class _104 {
    public static void main(String[] args) {
        _104 app = new _104();
        BinaryTreeNode root = app.createTree();
        int deep = app.maxDepth_v3(root);
        System.out.println("树的深度为" + deep);


    }

    /**
     * 参考版本
     * 递归：
     * 直观的方法是通过递归来解决问题。在这里，我们演示了 DFS（深度优先搜索）策略的示例。
     *
     * @param root
     * @return
     */
    public int maxDepth(BinaryTreeNode root) {
        System.out.println("当前root=" + (root == null ? "null" : root.Data));
        return root == null ? 0 : Math.max(maxDepth(root.LeftChild), maxDepth(root.RightChild)) + 1;

    }

    /**
     * 自己实现版本：递归求解树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth_v3(BinaryTreeNode root) {

        if (root == null) {
            return 0;
        }

        int lmax = maxDepth_v3(root.LeftChild);
        int rmax = maxDepth_v3(root.RightChild);

        return Math.max(lmax, rmax) + 1;


    }

    /**
     * 参考版本
     * 迭代：
     * 我们还可以在栈的帮助下将上面的递归转换为迭代。
     *
     * @param root
     * @return
     */
    public int maxDepth_v2(BinaryTreeNode root) {
        Queue<Pair<BinaryTreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<BinaryTreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            System.out.println("出栈元素为" + (root == null ? "null" : root.Data));
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.LeftChild, current_depth + 1));
                System.out.println("入栈元素为" + (root.LeftChild == null ? "null" : root.LeftChild.Data));
                stack.add(new Pair(root.RightChild, current_depth + 1));
                System.out.println("入栈元素为" + (root.RightChild == null ? "null" : root.RightChild.Data));
            }
        }
        return depth;

    }


    class BinaryTreeNode {
        public int Data;
        public BinaryTreeNode LeftChild;
        public BinaryTreeNode RightChild;

        public BinaryTreeNode(int data) {
            this.Data = data;
        }

        public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
            this.Data = data;
            this.LeftChild = left;
            this.RightChild = right;
        }
    }

    private void SetSubTreeNode(BinaryTreeNode root, BinaryTreeNode lChild, BinaryTreeNode rChild) {
        if (root == null) {
            return;
        }
        root.LeftChild = lChild;
        root.RightChild = rChild;
    }

    private BinaryTreeNode createTree() {
        /**
         *         //            1
         *         //         /      \
         *         //        2        3
         *         //       /\         \
         *         //      4  5         6
         *         //        /
         *         //       7
         */
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);

        SetSubTreeNode(node1, node2, node3);
        SetSubTreeNode(node2, node4, node5);
        SetSubTreeNode(node3, null, node6);
        SetSubTreeNode(node5, node7, null);

        return node1;
    }


}
