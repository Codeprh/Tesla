package swordFingerOffer.book;

import java.util.Stack;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-09-02 7:44 上午
 */
public class p27_mirrorTree {


    /**
     * 递归版本：
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree_right2(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree_right2(root.right);
        root.right = mirrorTree_right2(tmp);
        return root;
    }

    /**
     * 栈结构
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree_right(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<TreeNode>() {{
            add(root);
        }};
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }

    /**
     * noah版本：
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        return null;

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
