package geektime_datastructure_algorithm._8;


import java.util.Arrays;

/**
 * 描述:
 * 参考的实现
 * 基于数组实现的栈
 *
 * @author Noah
 * @create 2019-10-30 09:39
 */
public class _8_Stack_BaseArray {

    private String[] items; // 数组
    private int count; // 栈中元素个数
    private int n; //栈的大小

    // 初始化数组，申请一个大小为n的数组空间
    public _8_Stack_BaseArray(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    // 入栈操作
    public boolean push(String item) {
        // 数组空间不够了，直接返回false，入栈失败。
        if (count == n) return false;
        // 将item放到下标为count的位置，并且count加一
        items[count] = item;
        ++count;
        return true;
    }



    // 出栈操作
    public String pop() {
        // 栈为空，则直接返回null
        if (count == 0) return null;
        // 返回下标为count-1的数组元素，并且栈中元素个数count减一
        String tmp = items[count - 1];
        --count;
        return tmp;
    }

    /**
     * 创建字符串的栈
     *
     * @return
     */
    public static _8_Stack_BaseArray createStringNumStack() {

        _8_Stack_BaseArray stackBaseArray = new _8_Stack_BaseArray(10);
        for (int i = 0; i < stackBaseArray.n; i++) {
            stackBaseArray.push(String.valueOf(i));
        }
        return stackBaseArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);

    }

    public static void main(String[] args) {

        _8_Stack_BaseArray stackBaseArray = new _8_Stack_BaseArray(1);
        for (int i = 2; i > 0; i--) {
            stackBaseArray.push(String.valueOf(i));
        }
        System.out.println(stackBaseArray.toString());


    }

}
