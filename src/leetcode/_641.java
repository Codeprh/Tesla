package leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 * 设计循环双端队列
 *
 * @author Noah
 * @create 2019-12-03 10:10
 */
public class _641 {

    public static void main(String[] args) {
        _641 circularDeque = new _641(3); // 设置容量大小为3
        System.out.println(circularDeque.insertLast(1));// 返回 true
        System.out.println(circularDeque.insertLast(2));// 返回 true
        System.out.println(circularDeque.insertFront(3));// 返回 true
        System.out.println(circularDeque.insertFront(4));                    // 满了，返回 false
        System.out.println(circularDeque.getRear());// 返回 2
        System.out.println(circularDeque.isFull());// 返回 true
        System.out.println(circularDeque.deleteLast());// 返回 true
        System.out.println(circularDeque.insertFront(4));// 返回 true
        System.out.println(circularDeque.getFront());// 返回 4
    }

    /**
     * 参考：java.util.concurrent.LinkedBlockingDeque
     * <p>
     * 基于链表实现的双端阻塞队列
     *
     * @param <Integer>
     */
    static final class Node<Integer> {
        /**
         * The item, or null if this node has been removed.
         */
        Integer item;

        /**
         * One of:
         * - the real predecessor Node
         * - this Node, meaning the predecessor is tail
         * - null, meaning there is no predecessor
         */
        Node<Integer> prev;

        /**
         * One of:
         * - the real successor Node
         * - this Node, meaning the successor is head
         * - null, meaning there is no successor
         */
        Node<Integer> next;

        Node(Integer x) {
            item = x;
        }
    }

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     * (first.prev == null && first.item != null)
     */
    transient Node<Integer> first;

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     * (last.next == null && last.item != null)
     */
    transient Node<Integer> last;

    /**
     * Number of items in the deque
     */
    private transient int count;

    /**
     * Maximum number of items in the deque
     */
    private final int capacity;

    /**
     * Main lock guarding all access
     */
    final ReentrantLock lock = new ReentrantLock();

    /**
     * Condition for waiting takes
     */
    private final Condition notEmpty = lock.newCondition();

    /**
     * Condition for waiting puts
     */
    private final Condition notFull = lock.newCondition();

    /**
     * Creates a {@code LinkedBlockingDeque} with a capacity of
     * {@link Integer#MAX_VALUE}.
     */
    public _641() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public _641(int k) {
        if (k <= 0) throw new IllegalArgumentException();
        this.capacity = k;

    }

    /**
     * Removes and returns first element, or null if empty.
     */
    private Integer unlinkFirst() {
        // assert lock.isHeldByCurrentThread();
        Node<Integer> f = first;
        if (f == null)
            return null;
        Node<Integer> n = f.next;
        Integer item = f.item;
        f.item = null;
        f.next = f; // help GC
        first = n;
        if (n == null)
            last = null;
        else
            n.prev = null;
        --count;
        notFull.signal();
        return item;
    }

    /**
     * Removes and returns last element, or null if empty.
     */
    private Integer unlinkLast() {
        // assert lock.isHeldByCurrentThread();
        Node<Integer> l = last;
        if (l == null)
            return null;
        Node<Integer> p = l.prev;
        Integer item = l.item;
        l.item = null;
        l.prev = l; // help GC
        last = p;
        if (p == null)
            first = null;
        else
            p.next = null;
        --count;
        notFull.signal();
        return item;
    }

    private boolean linkFirst(Node<Integer> node) {
        // assert lock.isHeldByCurrentThread();
        if (count >= capacity)
            return false;
        Node<Integer> f = first;
        node.next = f;
        first = node;
        if (last == null)
            last = node;
        else
            f.prev = node;
        ++count;
        notEmpty.signal();
        return true;
    }

    /**
     * Links node as last element, or returns false if full.
     */
    private boolean linkLast(Node<Integer> node) {
        // assert lock.isHeldByCurrentThread();
        if (count >= capacity)
            return false;
        Node<Integer> l = last;
        node.prev = l;
        last = node;
        if (first == null)
            first = node;
        else
            l.next = node;
        ++count;
        notEmpty.signal();
        return true;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        final ReentrantLock lock = this.lock;
        Node<Integer> e = new Node<>(value);
        lock.lock();
        try {
            return linkFirst(e);
        } finally {
            lock.unlock();
        }


    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        final ReentrantLock lock = this.lock;
        Node<Integer> e = new Node<>(value);
        lock.lock();
        try {
            return linkLast(e);
        } finally {
            lock.unlock();
        }

    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return unlinkFirst() == null ? false : true;
        } finally {
            lock.unlock();
        }

    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return unlinkLast() == null ? false : true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return first == null ? -1 : first.item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return last == null ? -1 : last.item;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;

    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        if (count == capacity) {
            return true;
        }
        return false;
    }


}
