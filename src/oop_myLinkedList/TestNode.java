package oop_myLinkedList;

/**
 * 描述:
 * 测试主类
 *
 * @author codingprh
 * @create 2019-01-14 5:24 PM
 */
public class TestNode {

    public static void main(String[] args) {
//        MyLinkedList myLinkedList = MyLinkedList.newEmptyList();
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();


        MyLinkedList<String> stringMyLinkedList = new MyLinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            stringMyLinkedList.addNode(sb.append("a").toString());
        }

        for (int i = 0; i < 10; i++) {
            myLinkedList.addNode(i);
        }

        MyNode head = myLinkedList.getHead();

        for (Integer node : myLinkedList) {
            System.out.println(node);

        }
        for (String str : stringMyLinkedList) {
            System.out.println(str);
        }
        //MyNode.printListNode(head);


    }

}