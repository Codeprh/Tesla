package geektimeDatastructureAlgorithm._6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 描述:
 * 基于数组实现LRU缓存淘汰算法
 * <p>
 * LRU和LFU（最少访问）的区别？
 * <p>
 * 最近最少访问
 *
 * @author Noah
 * @create 2019-10-28 10:08
 */
public class _6_LRU_BaseArray<T> {

    public static final int DEFAULT_CAPACITY = 1 << 3;
    /**
     * 散列表:map.key是value的值，map.value是下标
     */
    private Map<T, Integer> holds;

    private Integer capacity;

    private int count;

    private T[] value;

    public _6_LRU_BaseArray() {
        this(DEFAULT_CAPACITY);
    }

    public _6_LRU_BaseArray(Integer capacity) {
        this.capacity = capacity;
        holds = new HashMap<>(capacity);
        value = (T[]) new Object[capacity];
    }

    /**
     * 访问命中某个缓存
     * <p>
     * a[0]是最新访问元素，a[n]是最旧的元素
     *
     * @param obj
     */
    public void offer(T obj) {

        Integer index = holds.get(obj);
        //不在数组中
        if (Objects.isNull(index)) {
            //缓存满了
            if (Objects.equals(count, capacity)) {
                removeAndCache(obj);
            } else {
                cache(obj, count);
            }
        } else {
            //在数组中
            update(index);

        }
    }

    /**
     * 不存在数组&&缓存满了
     */
    public void removeAndCache(T obj) {
        holds.remove(value[--count]);
        cache(obj, count);
    }

    /**
     * 不存在数组中
     */
    public void cache(T obj, int end) {
        shiftRight(end);
        value[0] = obj;
        holds.put(obj, 0);
        count++;
    }

    /**
     * 更新数组
     *
     * @param end
     */
    public void update(int end) {
        T target = value[end];
        shiftRight(end);
        value[0] = target;
        holds.put(target, 0);
    }

    /**
     * 把在end位置左边的元素，向右移动一位
     *
     * @param end
     */
    public void shiftRight(int end) {

        for (int i = end - 1; i >= 0; i--) {
            value[i + 1] = value[i];
            holds.put(value[i], i + 1);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(value[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        _6_LRU_BaseArray<Integer> app = new _6_LRU_BaseArray<>(2);
        app.offer(1);
        app.offer(2);
        app.offer(3);
        System.out.println(app.toString());
    }


}
