package leetcode;

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

    public static void main(String []args){


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
         *         //            1
         *         //         /      \
         *         //        2        3
         *         //       /\         \
         *         //      4  5         6
         *         //        /
         *         //       7
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
