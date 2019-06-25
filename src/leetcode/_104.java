package leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author codingprh
 * @create 2019-06-25 10:56
 */
public class _104 {
    public static void main(String[] args) {
        _104 app = new _104();
        BinaryTreeNode root = app.createTree();
        int deep = app.maxDepth_v2(root);
        System.out.println("树的深度为" + deep);


    }

    /**
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
