package swordFingerOffer;

import java.util.HashMap;
import java.util.Map;

/**
 * 重建二叉树
 * <p>
 * 题目：根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * 解题思路：前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。
 */
public class d5_RebuildingBinaryTree {

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        d5_RebuildingBinaryTree app = new d5_RebuildingBinaryTree();
        TreeNode node=app.reConstructBinaryTree(preorder, inorder);
        System.out.println(node);


    }


    // 缓存，中序遍历数组的每个值对应的索引
    private Map<Integer, Integer> inOrderNumsIndexs = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for (int i = 0; i < in.length; i++)
            inOrderNumsIndexs.put(in[i], i);
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL, int inR) {
        if (preL > preR)
            return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = inOrderNumsIndexs.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL, inL + leftTreeSize - 1);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1, inR);
        return root;
    }

}

/**
 * 二叉树节点
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
