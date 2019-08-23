package leetcode;

/**
 * @author Noah
 * @create 2019-08-22 09:37
 */
//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//
// 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
//写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
//
// 进阶:
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
// 示例:
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//System.out.println(cache.get(1));// 返回  1
//cache.put(3, 3);    // 该操作会使得密钥 2 作废
//System.out.println(cache.get(2));// 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得密钥 1 作废
//System.out.println(cache.get(1));// 返回 -1 (未找到)
//System.out.println(cache.get(3));// 返回  3
//System.out.println(cache.get(4));// 返回  4
//
//

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * todo：调试
 */
class LRUCache {

    private Integer capacity;
    private Map<Integer, Integer> map;
    private LinkedList<Integer> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        list = new LinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            list.remove((Integer) key);
            list.add(key);
            return map.get(key);
        }

    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove((Integer) key);
            map.put(key, value);
        } else if (map.size() >= this.capacity) {
            map.remove(list.removeFirst());
            map.put(key, value);
        } else {
            map.put(key, value);
        }
        list.add(key);
    }
}

public class _146 {


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}
