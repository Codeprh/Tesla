package geektime_datastructure_algorithm._23;

import java.util.*;

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
     * 最佳实践：完全二叉树使用数组来存储
     * <p>
     * - 下标为2 * i 的位置存储的就是左子节点
     * - 下标为2 * i + 1的位置存储 的就是右子节点
     * - 下标为i/2的位置存储就是它的父节点
     * - 根节点会存储在下标为1的位置
     */
    public String[] binaryTree2Array(TreeNode head) {

        List<List<String>> temp = levelOrder_v3(head);
        List<String> all = new ArrayList<>();

        for (List<String> list : temp) {
            all.addAll(list);
        }

        String[] strArr = all.toArray(new String[all.size()]);
        String[] r = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, r, 1, strArr.length);

        return r;
    }

    public String[] binaryTree2Array_v2(TreeNode head) {
        List<List<String>> temp = levelOrder_v3(head);

        String[] r = new String[10];
        int use = 1;
        for (List<String> list : temp) {

            int ls = list.size();
            System.arraycopy(list.toArray(), 0, r, use, ls);
            use = ls + use;
        }
        return r;
    }

    /**
     * 层次遍历:BFS（宽度优先搜索算法）
     *
     * @param head
     */
    public void levelOrder(TreeNode head) {

        if (head == null) {
            return;
        }

    }

    /**
     * 参考版：层次遍历==>递归实现||迭代实现
     */
    List<List<String>> levels = new ArrayList<List<String>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<String>());

        // fulfil the current level
        levels.get(level).add((String) node.data);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    /**
     * 参考版本：递归实现
     *
     * @param root
     * @return
     */
    public List<List<String>> levelOrder_v2(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }

    /**
     * 参考版本：迭代&&队列实现
     *
     * @param root
     * @return
     */
    public List<List<String>> levelOrder_v3(TreeNode root) {
        List<List<String>> levels = new ArrayList<List<String>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            // start the current level
            levels.add(new ArrayList<String>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add((String) node.data);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }


    /**
     * 前序遍历：DFS（深度优先搜索算法）
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
     * 中序遍历：DFS（深度优先搜索算法）
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
     * 后序遍历：DFS（深度优先搜索算法）
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

        //app.bfs(app, head);
        //app.dfs(app, head);

        String[] arrs = app.binaryTree2Array_v2(head);
        System.out.println(Arrays.toString(arrs));

        int i = 2;
        System.out.println("当前节点=" + arrs[i]);
        System.out.println("左节点=" + arrs[2 * (i)]);
        System.out.println("右节点=" + arrs[2 * i + 1]);


    }

    /**
     * bfs：宽度优先搜索算法
     *
     * @param app
     * @param head
     */
    public void bfs(TreeNode app, TreeNode head) {
        System.out.println(app.levelOrder_v2(head).toString());
        System.out.println(app.levelOrder_v3(head).toString());
    }

    /**
     * dfs：深度优先搜索算法
     */
    public void dfs(TreeNode app, TreeNode head) {
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
