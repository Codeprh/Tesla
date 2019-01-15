package oop_myLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 描述:
 * 自己创建的链表
 *
 * @author codingprh
 * @create 2019-01-12 7:52 AM
 */
public class MyLinkedList<T> implements Iterable<T> {

    private MyNode<T> head;
    private MyNode<T> tail;

    public MyNode getHead() {
        return head;
    }

    public MyNode getTail() {
        return tail;
    }

    public MyLinkedList() {
    }


    public static <T> MyLinkedList<T> newEmptyList() {
        return new MyLinkedList<T>();
    }

    public void addNode(T val) {
        MyNode<T> node = new MyNode<>(val);
        //初始化数据
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }


    private class MyListIterator implements Iterator<T> {
        private MyNode<T> currentNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            T val = currentNode.getValue();
            currentNode = currentNode.getNext();
            return val;
        }

        public MyListIterator(MyNode<T> currentNode) {
            this.currentNode = currentNode;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator(head);
    }


}