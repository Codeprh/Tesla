package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author codingprh
 * @create 2019-06-25 17:54
 */
public class _230 {
    class TreeNode {
        public int Data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.Data = data;
        }

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.Data = data;
            this.left = left;
            this.right = right;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        //寻找第k小的元素:中序遍历一个二叉查找树（BST）的结果是一个有序数组，
        //4275136
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> re = new ArrayList<>();
        //入栈操作
        while (true) {
            while (root != null) {
                stack.push(root);

                root = root.left;
            }

            root = stack.pop();

            re.add(root.Data);
            System.out.print(root.Data);

            root = root.right;
        }
    }

    public int kthSmallest_v2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            System.out.print(root.Data);
            if (--k == 0) return root.Data;
            root = root.right;
        }
    }


    public static void main(String[] args) {
        _230 start = new _230();
        TreeNode root = start.createTree();
        System.out.println(start.kthSmallest(root, 7));

    }

    public void print(TreeNode head) {

    }

    private void SetSubTreeNode(TreeNode root, TreeNode lChild, TreeNode rChild) {
        if (root == null) {
            return;
        }
        root.left = lChild;
        root.right = rChild;
    }

    private TreeNode createTree() {
        /**
         *        5
         *       / \
         *      3   6
         *     / \
         *    2   4
         *   /
         *  1
         */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        SetSubTreeNode(node1, node2, node3);
        SetSubTreeNode(node2, node4, node5);
        SetSubTreeNode(node3, null, node6);
        SetSubTreeNode(node5, node7, null);

        return node1;
    }
}
