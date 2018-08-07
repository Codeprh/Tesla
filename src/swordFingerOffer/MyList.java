package swordFingerOffer;

/**
 * 自己实现的链表结构
 */
public class MyList {
    //头结点
    Node head = null;

    /**
     * 自定义的节点
     */
    class Node {
        private Object val;
        private Node next = null;

        public Node(Object val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    /**
     * 新增一个节点
     *
     * @param val
     */
    public void addNode(Object val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * 打印节点的所有的数据
     */
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    /**
     * 获取下一个node
     *
     * @return
     */
    public Node getNodeNext(Node node) {
        if (node == null) {
            return head;
        }
        return node.next;
    }

}
