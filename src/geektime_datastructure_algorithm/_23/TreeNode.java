package geektime_datastructure_algorithm._23;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * <p>
 * 树:泛型数据类型的二叉树
 * <p>
 * 基于链表的存储结构
 *
 * @author Noah
 * @create 2019-11-14 10:39
 */
public class TreeNode<T> {

    public T data;
    public TreeNode left;
    public TreeNode right;

    public List<T> dataList;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode() {
        this.data = null;
        dataList = new ArrayList<>();
    }

    /**
     * 前序遍历
     *
     * @param head
     */
    public void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.data + " ");
        dataList.add((T) head.data);
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 中序遍历
     *
     * @param head
     */
    public void inOrder(TreeNode head) {

        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.print(head.data + " ");
        dataList.add((T) head.data);
        inOrder(head.right);

    }

    /**
     * 后序遍历
     *
     * @param head
     */
    public void postOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.data + " ");
        dataList.add((T) head.data);
    }

    public static void main(String[] args) {
        TreeNode<String> app = new TreeNode<>();

        TreeNode<String> head = createBinaryTree();

        app.preOrder(head);
        System.out.println(app.dataList.toString());
        System.out.println("");
        app.dataList.clear();

        app.inOrder(head);
        System.out.println(app.dataList.toString());
        System.out.println("");
        app.dataList.clear();

        app.postOrder(head);
        System.out.println(app.dataList.toString());
        System.out.println("");
        app.dataList.clear();
    }

    /**
     * 基于链表实现的string完全二叉树（满二叉树是完全二叉树的一种特殊情况）
     *
     * @return
     */
    public static <T> TreeNode createBinaryTree() {
        TreeNode a = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode g = new TreeNode("G");

        a.left = b;
        a.right = c;

        b.left = d;
        b.right = e;

        c.left = f;
        c.right = g;

        return a;

    }

}
