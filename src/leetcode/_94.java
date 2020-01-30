package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 中序遍历
 *
 * @author Noah
 * @create 2020-01-30 21:56
 */
public class _94 {


    public static void main(String[] args) {

    }

    List<Integer> r = new ArrayList<>();

    /**
     * 自己实现第一版
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        resver(root);
        return r;
    }

    public void resver(TreeNode root) {
        if (root == null) {
            return;
        }
        resver(root.left);
        r.add((int) root.val);
        resver(root.right);

    }
}
