package concurrent._20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 描述:
 * 线程安全的arrayList
 *
 * @author Noah
 * @create 2019-10-15 21:50
 */
public class _12_SafeArrayList<T> {
    /**
     * 实现线程安全的ArrayList，载体
     */
    private List<T> c = new ArrayList<>();

    /**
     * 线程安全：查找list的数据
     *
     * @param index
     * @return
     */
    public synchronized T get(int index) {
        return c.get(index);
    }

    /**
     * 线程安全：list中插入数据
     *
     * @param index
     * @param t
     */
    public synchronized void add(int index, T t) {
        c.add(index, t);
    }

    /**
     * 线程安全：数据不存在才插入
     *
     * @param t
     * @return
     */
    public synchronized boolean addIfNotExist(T t) {
        if (!c.contains(t)) {
            c.add(t);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        _12_SafeArrayList<Integer> safeArrayList = new _12_SafeArrayList<>();
        safeArrayList.addIfNotExist(10);
        System.out.println("r=" + safeArrayList.get(0));

        List<Integer> list = new ArrayList<>();
        List<Integer> safeList = Collections.synchronizedList(list);


    }


}
