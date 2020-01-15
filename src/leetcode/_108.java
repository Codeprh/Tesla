package leetcode;

import java.util.List;

/**
 * 描述:
 * <p>
 * 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * <p>
 * 正确的解题思路：
 *
 * 根据二叉搜索树的特点：中序遍历等到是一个升序的数组。
 *
 * 类似的题目：{@link leetcode._98}和_98
 * _105和_106
 *
 * @author Noah
 * @create 2020-01-13 18:32
 */
public class _108 {

    public static void main(String[] args) {

        int[] arrs = new int[]{-10,-3,0,5,9};
        _108 app = new _108();

        TreeNode treeNode=app.sortedArrayToBST_v1(arrs);
        List<List<Integer>> lists = TreeNode.LevelTraversal(treeNode);
        System.out.println(lists.toString());


    }

    /**
     * 错误的解题思路：
     * 数组存储高度平衡二叉查找树，顶点大于左子树，小于右子树。
     * <p>
     * * 最佳实践：完全二叉树使用数组来存储
     * <p>
     * * - 下标为2 * i+1 的位置存储的就是左子节点
     * * - 下标为2 * i + 2的位置存储 的就是右子节点
     * * - 下标为i/2的位置存储就是它的父节点
     * * - 根节点会存储在下标为的0位置
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        TreeNode head = new TreeNode(nums[0]);
        TreeNode p = head;

        for (int i = 0; i < nums.length; i++) {

        }
        return null;
    }

    //=====================================
    //=============递归版本=================
    //=====================================
    public TreeNode sortedArrayToBST_v1(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);

        return root;
    }

    //=====================================
    //=============二分查找思想版本==========
    //=====================================
    public TreeNode sortedArrayToBST_v2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return help(nums, 0, nums.length - 1);

    }


    private TreeNode help(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = help(nums, start, mid - 1);
        node.right = help(nums, mid + 1, end);
        return node;
    }
}

