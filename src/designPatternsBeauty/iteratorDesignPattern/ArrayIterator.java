package designPatternsBeauty.iteratorDesignPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 描述:
 * 线性迭代器：Array
 *
 * @author Noah
 * @create 2020-06-29 06:37
 */
public class ArrayIterator<E> implements IteratorV1<E> {

    private int cursor;
    private ArrayList<E> arrayList;

    public ArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return cursor != arrayList.size();
    }

    @Override
    public void next() {
        cursor++;

    }

    @Override
    public E currentItem() {

        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }

        return arrayList.get(cursor);
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(8);
        list.add(2);

        Iterator<Integer> iter1 = list.iterator();//snapshot: 3, 8, 2
        list.remove(new Integer(2));//list:3, 8
        Iterator<Integer> iter2 = list.iterator();//snapshot: 3, 8
        list.remove(new Integer(3));//list:8
        Iterator<Integer> iter3 = list.iterator();//snapshot: 3

        // 输出结果:3 8 2
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();

        // 输出结果:3 8
        while (iter2.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();
    }

    /**
     * 遍历的同时增删集合元素,不可预期行为
     */
    public static void delete() {
        //结果不可预期行为，未决行为
        ArrayList<String> names = new ArrayList<>();

        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        //IteratorV1<String> iterator = new ArrayIterator(names);
        Iterator<String> iterator = names.iterator();
        //游标指向的b
        System.out.println(iterator.next());
        //因为你删除了a元素，数组的删除操作会涉及元素的搬移，此时iterator只能遍历到c，d元素了（理论上要遍历到b,c,d）
        //如果删除的不是a元素的话，那iterator是正常的。
        names.remove("a");
        iterator.next();

    }

    /**
     * 遍历的同时增删集合元素,不可预期行为
     */
    public static void add() {
        ArrayList<String> names = new ArrayList<>();

        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        IteratorV1<String> iterator = new ArrayIterator(names);
        //游标指向的b，已经跳过了元素a
        iterator.next();
        //我们将 x 插入到下标为 0 的位置，a、b、 c、d 四个元素依次往后移动一位。这个时候，游标又重新指向了元素 a。元素 a 被游标重 复指向两次，也就是说，元素 a 存在被重复遍历的情况。
        //如果我们在游标的后面添加元素，就不会存在任何问题。所以，在遍历的 同时添加集合元素也是一种不可预期行为。
        names.add(0, "x");
        iterator.next();
    }

    private static void twoItrRemove() {
        ArrayList<String> names = new ArrayList<>();

        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        //内部类
        Iterator<String> iterator1 = names.iterator();
        Iterator<String> iterator2 = names.iterator();

        System.out.println(iterator1.next());

        iterator1.remove();
        System.out.println(iterator1.next());
        System.out.println(iterator2.next());

    }
}
