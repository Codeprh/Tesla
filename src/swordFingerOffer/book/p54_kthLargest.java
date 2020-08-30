package swordFingerOffer.book;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * @author Noah
 * @create 2020-08-30 9:40 上午
 */
public class p54_kthLargest {

    public static void main(String[] args) {

        p54_kthLargest app = new p54_kthLargest();
        TreeNode root = null;

        System.out.println(app.kthLargest(root, 1));

    }

    /**
     * noah版本：
     * 1、第k大值，求top k,堆
     * 2、二叉搜索树，中序遍历的，等到的是有序数组
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        orderTraverse(root, list);
        return list.get(list.size() - k);
    }

    public void orderTraverse(TreeNode treeNode, List<Integer> list) {

        if (treeNode == null) {
            return;
        }
        orderTraverse(treeNode.left, list);
        list.add(treeNode.val);
        orderTraverse(treeNode.right, list);

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
