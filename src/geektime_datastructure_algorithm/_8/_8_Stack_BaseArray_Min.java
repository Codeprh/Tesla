package geektime_datastructure_algorithm._8;


import java.util.Arrays;

/**
 * 描述:
 * <p>
 * 参考的实现
 * 基于数组实现的栈,双栈，找到栈中最小的元素
 * <p>
 * 定义栈的数据结构，在该类型中实现一个能够得到栈最小元素的 min 函数。
 *
 * @author Noah
 * @create 2019-10-30 09:39
 */
public class _8_Stack_BaseArray_Min {

    private int[] items; // 数组
    private int count; // 栈中元素个数
    private int n; //栈的大小

    //辅助栈的定义
    private Integer[] auxiliaryItems;
    private int auxiliaryCount;
    private int auxiliaryN;

    // 初始化数组，申请一个大小为n的数组空间
    public _8_Stack_BaseArray_Min(int n) {
        this.items = new int[n];
        this.n = n;
        this.count = 0;

        //辅助栈
        this.auxiliaryCount = 0;
        this.auxiliaryItems = new Integer[n];
        this.auxiliaryN = n;

    }

    // 入栈操作
    public boolean push(Integer item) {
        // 数组空间不够了，直接返回false，入栈失败。
        if (count == n) return false;
        // 将item放到下标为count的位置，并且count加一
        items[count] = item;
        ++count;

        //入栈元素小于辅助栈顶的元素，压入辅助栈
        if (auxiliaryCount == 0 || item < auxiliaryItems[auxiliaryCount - 1]) {

            if (auxiliaryCount == (auxiliaryN)) {
                System.out.println("当前辅助栈达到了最大长度，无法入栈了。元素为::" + Arrays.toString(auxiliaryItems));
                return false;
            }

            auxiliaryItems[auxiliaryCount] = item;
            ++auxiliaryCount;
        }
        return true;
    }


    // 出栈操作
    public Integer pop() {
        // 栈为空，则直接返回null
        if (count == 0) return null;
        // 返回下标为count-1的数组元素，并且栈中元素个数count减一
        Integer tmp = items[count - 1];
        --count;
        //辅助栈栈顶的元素等于出栈元素，则出栈
        if (tmp.equals(auxiliaryItems[auxiliaryCount - 1])) {
            --auxiliaryCount;
        }
        return tmp;
    }

    /**
     * 获取栈中最小的元素
     */
    public Integer getMin() {
        return auxiliaryItems[auxiliaryCount - 1];
    }

    /**
     * 创建字符串的栈
     *
     * @return
     */
    public static _8_Stack_BaseArray_Min createIntegerNumStack() {

        _8_Stack_BaseArray_Min stackBaseArray = new _8_Stack_BaseArray_Min(10);
        for (int i = 0; i < stackBaseArray.n; i++) {
            stackBaseArray.push(Integer.valueOf(i));
        }
        return stackBaseArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    public static void main(String[] args) {

        _8_Stack_BaseArray_Min stackBaseArray = new _8_Stack_BaseArray_Min(10);
        for (int i = 3; i > 0; i--) {
            stackBaseArray.push(i);
        }
        System.out.println("最小元素为" + stackBaseArray.getMin() + "，当前栈的情况" + Arrays.toString(stackBaseArray.auxiliaryItems));
        stackBaseArray.push(-1);
        System.out.println("最小元素为" + stackBaseArray.getMin() + "，当前栈的情况" + Arrays.toString(stackBaseArray.auxiliaryItems));
        stackBaseArray.pop();
        stackBaseArray.pop();
        System.out.println("主栈=" + Arrays.toString(stackBaseArray.items) + "，辅助栈=" + Arrays.toString(stackBaseArray.auxiliaryItems));
        System.out.println("最小元素为" + stackBaseArray.getMin() + "，当前栈的情况" + Arrays.toString(stackBaseArray.auxiliaryItems));


    }

}
