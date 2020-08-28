package designPatternsBeauty.iteratorDesignPattern;

/**
 * 迭代器模式，接口定义方式二
 * 返回当前元素与后移一位这两个操作，要放到同一个函 数 next() 中完成
 *
 * @param <E>
 */
public interface IteratorV2<E> {
    boolean hasNext();

    E next();
}
