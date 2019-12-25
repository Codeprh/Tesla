package swordFingerOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 * 21. 调整数组顺序使奇数位于偶数前面。原地排序算法。1、2、3、4、5==》1、3、5、2、4
 *
 * @author Noah
 * @create 2019-12-24 22:05
 */
public class _21 {

    public static void main(String[] args) {

        _21 app = new _21();
        int[] arr = new int[]{1, 2, 3, 4, 5};

        app.reOrderArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * todo:真的那么简单吗？
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     *
     * @param array
     */
    public void reOrderArray(int[] array) {
        //奇数list
        List<Integer> oddNumberList = new ArrayList<>();
        //偶数List
        List<Integer> evenNumberList = new ArrayList<>();

        for (int a : array) {

            if (a % 2 == 0) {
                evenNumberList.add(a);
            } else {
                oddNumberList.add(a);
            }
        }
        oddNumberList.addAll(evenNumberList);
        int[] os = new int[oddNumberList.size()];

        for (int i = 0; i < os.length; i++) {
            os[i] = oddNumberList.get(i);
        }


        System.arraycopy(os, 0, array, 0, array.length);

    }
}
