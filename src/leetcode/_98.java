package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 验证二叉查找树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 *
 * @author Noah
 * @create 2019-12-15 21:33
 */
public class _98 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.creatBinarySearchTreesByLinked(false);

        _98 app = new _98();
        System.out.println("当前二叉树是查找树吗？" + app.isValidBST_v1(treeNode));
    }

    /**
     * 验证是一棵二叉查找树：1、根节点>左子树&&根节点<右子树
     * <p>
     * //采用递归实现
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {


        return helper(root, null, null);
    }

    public boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = (int) node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }

    private List<Integer> list = new ArrayList<>();

    /**
     * 利用中序遍历，等到的是一个有序的数组的特点，来判定
     *
     * @param root
     * @return
     */
    public boolean isValidBST_v1(TreeNode root) {

        inorderTraversal(root);
        int preTemp = Integer.MIN_VALUE;
        for (Integer aa : list) {
            if (aa < preTemp) {
                return false;
            }
            preTemp = aa;
        }
        return true;
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        list.add((int) node.val);
        inorderTraversal(node.right);

    }


}

