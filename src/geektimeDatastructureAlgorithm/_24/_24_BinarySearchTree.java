package geektimeDatastructureAlgorithm._24;

/**
 * 描述:
 * <p>
 * 参考版
 * 二叉查找树：对于任意一个节点，左节点都小于该节点，右节点都大于该节点。
 *
 * @author Noah
 * @create 2019-11-14 09:36
 */
public class _24_BinarySearchTree {

    private Node tree;

    /**
     * 二叉查找树：查询操作
     *
     * @param data
     * @return
     */
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    /**
     * 二叉查找树：插入操作
     *
     * @param data
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else { // data < p.data
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除操作
     *
     * @param data
     */
    public void delete(int data) {
        Node p = tree; // p指向要删除的节点，初始化指向根节点
        Node pp = null; // pp记录的是p的父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        if (p == null) return; // 没有找到

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            Node minP = p.right;
            Node minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    public static void main(String[] args) {
        _24_BinarySearchTree app = new _24_BinarySearchTree();
        app.createBinarySearchTree();
        app.insert(1);
        app.inOrder(app.tree);
    }

    /**
     * 二叉查找树的中序遍历:从小到大输出数据
     */
    public void inOrder(Node head) {

        if (head == null) {
            return;
        }

        inOrder(head.left);
        System.out.print(head.data + " ");
        inOrder(head.right);

    }

    /**
     * 创建二叉查找树
     *
     * @return
     */
    public Node createBinarySearchTree() {
        Node _13 = new Node(13);
        Node _10 = new Node(10);
        Node _16 = new Node(16);
        Node _9 = new Node(9);
        Node _11 = new Node(11);
        Node _14 = new Node(14);

        _13.left = _10;
        _13.right = _16;

        _10.left = _9;
        _10.right = _11;

        _16.left = _14;

        tree = _13;
        return tree;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

}
