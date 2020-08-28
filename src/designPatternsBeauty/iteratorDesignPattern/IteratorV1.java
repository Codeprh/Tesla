package designPatternsBeauty.iteratorDesignPattern;

/**
 * 迭代器模式，接口定义方式一
 * next() 函数用来将游标后移一位元素，currentItem() 函数用来返回当前 游标指向的元素。
 * <p>
 * 第一种定义方式更加灵活一些，比如我们可以多次调用 currentItem() 查询当前元素，而不 移动游标。所以，在接下来的实现中，我们选择第一种接口定义方式。
 *
 * @param <E>
 */
public interface IteratorV1<E> {
    boolean hasNext();

    void next();

    E currentItem();
}
