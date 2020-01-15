package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 106. 从中序与后序遍历序列构造二叉树
 * <p>
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 栗子：
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * <p>
 * //   3
 * //  / \
 * // 9  20
 * //   /  \
 * //  15   7
 *
 * @author Noah
 * @create 2020-01-15 09:45
 */
public class _106 {

    public static void main(String[] args) {
        _106 app = new _106();
        TreeNode head = app.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println(TreeNode.LevelTraversal(head).toString());

    }

    //=======================
    //=========错误自己实现===
    //=======================
    int[] inorder;
    int[] postorder;
    int postIndex;
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        this.inorder = inorder;
        this.postorder = postorder;
        int inIndex = 0;
        postIndex = postorder.length;

        for (int aa : inorder) {
            map.put(aa, inIndex++);
        }

        return helper(0, inIndex);
    }


    public TreeNode helper(int inLeft, int inRight) {

        if (inLeft == inRight) {
            return null;
        }

        int lastPostIndex = postIndex - 1;
        int post = postorder[lastPostIndex];

        TreeNode root = new TreeNode(post);

        int inIndex = map.get(post);
        postIndex--;
        root.left = helper(inLeft, inIndex);
        root.right = helper(inIndex + 1, inRight);

        return root;
    }
}
